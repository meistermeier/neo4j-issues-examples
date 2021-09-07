package com.example.discourse44037;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
@Data
public final class StepDescEntity {
    @Id
    @GeneratedValue
    private final Long id;

    @TargetNode
    private final StepEntity stepEntity;

    private final String value;
}
