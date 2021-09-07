package com.example.discourse44037;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface CocktailRepository extends Neo4jRepository<CocktailEntity, String> {

    @Query("match (c:Cocktail)-[r:DESCRIPTION]-(s:Step) return c,collect(r),collect(s)")
    List<CocktailEntity> allCocktails();
}
