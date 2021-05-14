package com.example.gh2176;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

public abstract class BaseEntity<T> {

    @Id
    @GeneratedValue(generatorClass = SomeStringGenerator.class)
    private String Id;
}
