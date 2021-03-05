package com.meistermeier.neo4j.relatedIdQuery;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PersonRepository extends Neo4jRepository<Person, Long> {
}
