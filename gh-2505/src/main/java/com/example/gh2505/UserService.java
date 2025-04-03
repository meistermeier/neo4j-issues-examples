package com.example.gh2505;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gerrit Meier
 */
@Service
public class UserService {

	@Autowired
	Neo4jTemplate neo4jTemplate;

	@Autowired
	UserRepository repository;

//	@Transactional(readOnly = true)
	public void findAllUsers() {
		neo4jTemplate.findAll(User.class);
	}

@Transactional(transactionManager = "transactionManager", readOnly = true)
public void doThings() {
		repository.findByName("blubb");
	}
}
