package com.meistermeier.gh2952;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author Gerrit Meier
 */
public interface UserRepository extends Neo4jRepository<User, String>, QuerydslPredicateExecutor<User> {
}
