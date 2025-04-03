package com.example.gh2505;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.core.Neo4jTemplate;

/**
 * @author Gerrit Meier
 */
@SpringBootTest
public class SomeRoutingTest {

	@Autowired
	UserRepository repository;

	@Autowired
	Neo4jTemplate neo4jTemplate;

	@Autowired
	UserService userService;

	@Test
	void asdf() {
//		userService.findAllUsers();
		userService.doThings();
	}

}
