package com.example.gh2505;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.core.ReactiveNeo4jTemplate;
import reactor.test.StepVerifier;

@SpringBootTest
class Gh2505ApplicationTests {

	private final ReactiveNeo4jTemplate neo4jTemplate;

	@Autowired
	Gh2505ApplicationTests(ReactiveNeo4jTemplate neo4jTemplate) {
		this.neo4jTemplate = neo4jTemplate;
	}

	@Test
	void storeUser() {
		User u = new User(true);
		StepVerifier.create(neo4jTemplate.save(User.class).one(u))
			.expectNextCount(1L)
			.verifyComplete();
	}

}
