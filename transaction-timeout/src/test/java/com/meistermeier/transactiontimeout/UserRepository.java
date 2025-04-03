package com.meistermeier.transactiontimeout;

import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * @author Gerrit Meier
 */
public interface UserRepository extends Neo4jRepository<User, String> {
}
