package com.meistermeier.discourse71553;

import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * @author Gerrit Meier
 */
public interface UserRepository extends Neo4jRepository<User, String> {

    User findByName(String name);
}
