package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationTests {

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
		String documentId = null;
		try (Session session = driver.session()) {
			documentId = session.run("CREATE (d:Document{id: 'DID', name:'A'})-[:CONCERNS]->(:User{id: 'UID', name:'B'}) return d.id as id")
					.single().get("id").asString();
		}

		Document document = documentRepository.findById(documentId).get();
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

}
