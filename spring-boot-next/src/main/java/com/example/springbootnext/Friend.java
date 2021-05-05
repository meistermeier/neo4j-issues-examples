package com.example.springbootnext;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Friend {

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

}
