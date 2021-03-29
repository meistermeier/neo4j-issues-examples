package com.example.demo;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Purchase")
public class PurchaseEntity {

    @Id
    @GeneratedValue
    private Long id;

}
