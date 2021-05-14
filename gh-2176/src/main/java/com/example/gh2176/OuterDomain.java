package com.example.gh2176;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Node;

@Getter
@Setter
@Node
public abstract class OuterDomain extends BaseEntity<OuterDomain> {

    @Getter
    @Setter
    @Node
    public static class InnerDomain extends OuterDomain {
        // constructors
    }
}
