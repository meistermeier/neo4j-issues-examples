package com.example.gh2680;

import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * @author Gerrit Meier
 */
public interface MaterialRepository extends Neo4jRepository<Material, Long> {
}
