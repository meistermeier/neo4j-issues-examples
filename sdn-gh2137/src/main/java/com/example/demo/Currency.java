package com.example.demo;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Currency {
    @Id
    private String name;

    public String getName() {
        return name;
    }
}
