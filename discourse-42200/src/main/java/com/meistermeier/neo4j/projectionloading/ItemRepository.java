package com.meistermeier.neo4j.projectionloading;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface ItemRepository extends Neo4jRepository<ItemEntity, Integer> {

    @Query("MATCH (i:Item)"
            + " WHERE toLower (COALESCE(i.firstField,\" \")"
            + ") CONTAINS $englishKeyword"
            + " OR "
            + "COALESCE(i.secondField,\" \")"
            + "CONTAINS $russianKeyword"
            + " RETURN i"
    )
    List<ItemSubset> findWithKeywords(String englishKeyword, String russianKeyword);

}
