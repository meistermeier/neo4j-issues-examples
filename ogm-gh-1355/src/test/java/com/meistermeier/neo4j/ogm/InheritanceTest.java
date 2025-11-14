package com.meistermeier.neo4j.ogm;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.ogm.drivers.bolt.driver.BoltDriver;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author Gerrit Meier
 */
class InheritanceTest {

		Session session;
		Driver driver;

		@BeforeEach
		void setup() {
				driver = GraphDatabase.driver("neo4j://localhost:7687", AuthTokens.basic("neo4j", "verysecret"));
				var boltDriver = new BoltDriver(driver);
				var sf = new SessionFactory(boltDriver, "com.meistermeier.neo4j.ogm");
				session = sf.openSession();
				session.purgeDatabase();
		}

		@Test
		void inheritanceWithConcreteTypeShouldWork() {
				Document doc= new Document();
				doc.setType("firstType");
				session.save(doc);

				Product product = new Product();
				product.setCategory("someCat");
				product.setProductCode("myCode");
				session.save(product);

				// load and resave
				Iterable<BaseEntity> loadedEntities = session.loadAll(BaseEntity.class);
				session.save(loadedEntities);

				try (var session = driver.session()) {
						var nodes = session.run("MATCH (d:BaseEntity) return d").list(record -> record.get("d").asNode());
						nodes.forEach(node -> {
								assertTrue(node.hasLabel("BaseEntity") && (node.hasLabel("Document") ^ node.hasLabel("Product")));
						});
				}
		}
}
