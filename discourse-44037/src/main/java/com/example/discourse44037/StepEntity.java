package com.example.discourse44037;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node(value = "Step")
@Data
public final class StepEntity {
    @Id
    private int number;

}
