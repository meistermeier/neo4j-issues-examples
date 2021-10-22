package com.example.discourse44560;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface EntityRepository extends Neo4jRepository<Entity, String> {

    RelatedEntityProjection findByName(String name);

}
