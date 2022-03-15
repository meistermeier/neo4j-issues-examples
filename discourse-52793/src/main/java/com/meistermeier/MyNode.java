package com.meistermeier;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
public class MyNode {

    @Id
    String id;
    String name;
    String type;

    @Relationship(type = "CONTAINS")
    Set<MyEdge> outgoingEdges = new HashSet<>();

    public MyNode() {
    }

    public MyNode(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    // Getters and setters... 
}
