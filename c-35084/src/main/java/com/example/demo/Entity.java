package com.example.demo;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node(primaryLabel = "Entity")
public class Entity {

    @Id
    @GeneratedValue
    private Long id;

    private String someProperty;

    @Relationship(type = "some_rel", direction = Relationship.Direction.OUTGOING)
    private List<Entity2> otherEntities;

}
