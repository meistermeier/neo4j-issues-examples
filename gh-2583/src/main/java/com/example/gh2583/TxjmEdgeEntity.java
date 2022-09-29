package com.example.gh2583;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.CompositeProperty;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.HashMap;
import java.util.Map;

@RelationshipProperties
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TxjmEdgeEntity {

    @RelationshipId
    @EqualsAndHashCode.Include
    private Long id;
    @GeneratedValue(UUIDStringGenerator.class)
    @Property
    private String uid;
    @Property
    private String name;
    @Property
    private String shape;
    @Property
    private String subShape;
    @Property
    private String subShapeName;

    @CompositeProperty
    private Map<String, String> props = new HashMap<>();

    @TargetNode
    private TxjmNodeEntity targetNode;


}