package com.example.discourse44037;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CocktailRepository extends Neo4jRepository<CocktailEntity, String> {
}
