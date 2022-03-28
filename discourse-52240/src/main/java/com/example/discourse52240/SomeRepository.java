package com.example.discourse52240;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface SomeRepository extends Neo4jRepository<SomeEntity, Long> {
}
