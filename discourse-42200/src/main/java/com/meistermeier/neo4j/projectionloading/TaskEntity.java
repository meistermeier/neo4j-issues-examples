package com.meistermeier.neo4j.projectionloading;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class TaskEntity {

    @Id @GeneratedValue
    private Long id;
}
