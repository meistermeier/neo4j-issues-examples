package com.meistermeier.gh2898;

import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Gh2898ApplicationTests {

	@Autowired
	Driver driver;

	@Autowired
	ConceptRepository repository;

	@Test
	void contextLoads() {
		try (var session = driver.session()) {
			session.executeWriteWithoutResult(tx -> tx.run("MATCH (n) detach delete n").consume());
			session.executeWriteWithoutResult(tx -> tx.run("CREATE (c:Concept)-[:LINKED{relation:'wurst'}]->(:Concept)").consume());
		}

		System.out.println(repository.test());
	}

}
