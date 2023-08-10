package com.example.jsonignore;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

/**
 * @author Gerrit Meier
 */
public interface OwnerRepository extends ReactiveNeo4jRepository<Owner, String> {
}
