package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.AbstractNeo4jConfig;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
class DemoApplicationTests {

	@Container
	private static Neo4jContainer<?> neo4jContainer = new Neo4jContainer<>(DockerImageName.parse("neo4j").withTag("4.1"))
			.withoutAuthentication();

	private final Driver driver;

	DemoApplicationTests(@Autowired Driver driver) {
		this.driver = driver;
	}

	@BeforeEach
	void cleanup() {
		try (Session session = driver.session()) {
			session.run("MATCH (n) detach delete n").consume();
		}
	}

	@Test
	void saveUnidirectional(@Autowired GroupRepository repository) {
		Group group = new Group();
		group.setName("Group1");
		Device device = new Device();
		device.setName("device1");
		group.setDevices(Collections.singleton(device));

		repository.save(group);

		try (Session session = driver.session()) {
			List<Record> result = session.run("MATCH (g:Group)-[:IS_LINKED]->(d:Device) return g").list();
			assertThat(result).hasSize(1);
		}
	}

	@Test
	void saveBidirectional(@Autowired GroupRepository repository) {
		Group group = new Group();
		group.setName("Group1");
		Device device = new Device();
		device.setName("device1");
		group.setDevices(Collections.singleton(device));
		device.setGroups(Collections.singleton(group));

		repository.save(group);

		try (Session session = driver.session()) {
			List<Record> result = session.run("MATCH (g:Group)-[:IS_LINKED]->(d:Device) return g").list();
			assertThat(result).hasSize(1);
		}
	}

	@Test
	void findByCustomQuery(@Autowired DeviceRepository repository) {
		try (Session session = driver.session()) {
			session.run("CREATE (g:Group{groupId:1}), " +
					"(g)-[:IS_LINKED]->(:Device{name:'d1'}), " +
					"(g)-[:IS_LINKED]->(:Device{name:'d2'}) "
					).consume();
		}

		assertThat(repository.deviceByGroupId(1L, 1)).hasSize(1);
		assertThat(repository.deviceByGroupId(1L, 2)).hasSize(2);
		assertThat(repository.deviceByGroupId(1L, 20)).hasSize(2);

	}

	@Test
	void removeRelationship(@Autowired DocumentRepository documentRepository) {
		try (Session session = driver.session()) {
			session.run("CREATE (d:Document{name:'A', id:'A', version: 0})-[:CONCERNS]->(:User{name:'B', id:'B'})")
					.consume();
		}

		Document document = documentRepository.findById("A").get();
		document.setConcerns(null);
		documentRepository.save(document);

		try (Session session = driver.session()) {
			List<Record> list = session.run("MATCH (d:Document) return d").list();
			assertThat(list).hasSize(1);

			// now with the non-existing relationship
			list = session.run("MATCH (d:Document)-[:CONCERNS]->(:User) return d").list();
			assertThat(list).hasSize(0);
		}
	}

	@Configuration
	@EnableTransactionManagement
	@EnableNeo4jRepositories
	static class Config extends AbstractNeo4jConfig {

		@Bean
		public Driver driver() {
			return GraphDatabase.driver(neo4jContainer.getBoltUrl());
		}
	}

}
