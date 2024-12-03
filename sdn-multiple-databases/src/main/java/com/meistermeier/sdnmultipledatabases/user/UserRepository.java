package com.meistermeier.sdnmultipledatabases.user;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Gerrit Meier
 */
@NoRepositoryBean
public interface UserRepository extends Neo4jRepository<User, String> {
}
