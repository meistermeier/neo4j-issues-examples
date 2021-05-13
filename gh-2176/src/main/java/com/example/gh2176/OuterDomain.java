package com.example.gh2176;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Getter
@Setter
@Node
public abstract class OuterDomain {

    @Id
    @GeneratedValue
    private Long Id;

    // base properties and relationships

    @Getter
    @Setter
    @Node
    public static class InnerDomain extends OuterDomain {
        // constructors
    }
}
