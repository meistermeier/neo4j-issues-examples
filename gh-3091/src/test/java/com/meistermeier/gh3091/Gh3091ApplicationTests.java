package com.meistermeier.gh3091;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.AbstractNeo4jConfig;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.testcontainers.neo4j.Neo4jContainer;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Gh3091ApplicationTests {

		@Autowired
		Entity1Repository repository;

		@Autowired
		Driver driver;

		static Neo4jContainer neo4jContainer;

		static String entity1Id;

		@BeforeAll
		static void containerSetup() {
				neo4jContainer = new Neo4jContainer("neo4j:2026.03").withAdminPassword("verysecret");
				neo4jContainer.start();
		}

		@AfterAll
		static void containerShutdown() {
				neo4jContainer.stop();
		}

		@BeforeEach
		void setup() {
				try (var session = driver.session()) {
						session.executeWriteWithoutResult(tx -> {
								tx.run("MATCH (n) DETACH DELETE n");
								entity1Id = tx.run("CREATE (e:Entity1{name: 'entity1'})-[:RELATED_TO]->(:Entity2{name:'entity2'}) RETURN elementId(e) as id").next().get("id").asString();
						});
				}
		}

		@Test
		void saveEntities() {
				var entity1 = repository.findById(entity1Id).get();

				var relationshipId = entity1.getEntities().get(0).getId();

				repository.save(entity1);

				var newRelationshipId = repository.findById(entity1Id).get().getEntities().get(0).getId();

				assertThat(newRelationshipId).isEqualTo(relationshipId);
		}

		@Test
		void addRelationshipToNewEntity() {
				var entity1 = repository.findById(entity1Id).get();

				var entity2 = new Entity2();
				entity2.setName("New Name");
				var newRel = new EntityRelationship();
				newRel.setEntity2(entity2);
				entity1.getEntities().add(newRel);

				repository.save(entity1);
				var updatedEntity = repository.findById(entity1Id).get();

				assertThat(updatedEntity.getEntities()).hasSize(2);
		}

		@Test
		void addRelationshipToExistingEntity() {
				var entity1 = repository.findById(entity1Id).get();

				var existingEntity2 = entity1.getEntities().get(0).getEntity2();
				var newRel = new EntityRelationship();
				newRel.setEntity2(existingEntity2);
				entity1.getEntities().add(newRel);

				repository.save(entity1);
				var updatedEntity = repository.findById(entity1Id).get();

				assertThat(updatedEntity.getEntities()).hasSize(2);
		}

		@Configuration
		@EnableTransactionManagement
		@EnableNeo4jRepositories
		static class Config extends AbstractNeo4jConfig {

				@Bean
				public Driver driver() {
						return GraphDatabase.driver(neo4jContainer.getBoltUrl(), AuthTokens.basic("neo4j", "verysecret"));
				}
		}

}
