package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Getter
@Setter
@Node
@AllArgsConstructor
public class Document {

    @Id
    private final String id;

    private final String name;

    @Relationship("CONCERNS")
    private Concerns concerns;

    @Version
    private Long version;
}
