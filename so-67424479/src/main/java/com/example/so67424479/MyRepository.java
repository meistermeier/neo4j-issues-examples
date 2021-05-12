package com.example.so67424479;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface MyRepository extends Neo4jRepository<MyEntity, Long> {
}
