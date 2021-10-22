package com.example.discourse44560;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Node("Entity")
@Data
public class Entity {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    private String name;

    @Relationship
    private Map<String, List<RelatedEntity>> relatedEntities = new HashMap<>();

}
