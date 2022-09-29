package com.example.gh2592;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.CompositeProperty;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Node("hktxjm")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TxjmNodeEntity {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    Long id;
    @GeneratedValue(UUIDStringGenerator.class)
    @EqualsAndHashCode.Include
    @Property
    String uid;

    String idd;

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

    @Relationship(type = "guanXi", direction = Relationship.Direction.INCOMING)
    private List<TxjmNodeEntity> edgeIncoming;

    @Relationship(type = "guanXi", direction = Relationship.Direction.OUTGOING)
    private List<TxjmNodeEntity> edgeOutgoing;



}