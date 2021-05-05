package com.meistermeier.customrepository;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.Objects;

@Node
public abstract class DatabaseObject {

    @Id
    @GeneratedValue
    Long id;

}
