package com.example.so69577888;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Movie {

    @Id
    public String title;
}
