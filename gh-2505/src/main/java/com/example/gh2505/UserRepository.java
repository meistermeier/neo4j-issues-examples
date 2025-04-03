package com.example.gh2505;

import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * @author Gerrit Meier
 */
public interface UserRepository extends Neo4jRepository<User, Long> {

	User findByName(String name);
}
