package com.meistermeier.gh2898;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.io.Serializable;

@Data
@RelationshipProperties
public class CCLink implements Serializable {

    @RelationshipId
    @GeneratedValue
    private Long id;

    @TargetNode
    private Concept relatedConcept;

    @Property
    private String relation;

    public CCLink(Concept relatedConcept, String relation) {
        this.relatedConcept = relatedConcept;
        this.relation = relation;
    }
}