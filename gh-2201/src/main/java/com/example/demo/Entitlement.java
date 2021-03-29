package com.example.demo;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Entitlement")
public class Entitlement {

    @Id
    @GeneratedValue
    private Long id;

}
