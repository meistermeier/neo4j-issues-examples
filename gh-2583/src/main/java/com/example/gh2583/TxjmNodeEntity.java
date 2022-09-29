package com.example.gh2583;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TxjmNodeEntity {
    @Id
    @EqualsAndHashCode.Include
    Long id;
    @GeneratedValue(UUIDStringGenerator.class)
    @EqualsAndHashCode.Include
    @Property
    String uid;
    @Property
    private String name;
    @Property
    private String shape;
    @Property
    private String subShape;
    @Property
    private String subShapeName;

//    @DynamicLabels
//    public Set<String> moreLabels;

    @CompositeProperty
    private Map<String, String> props = new HashMap<>();

    @Relationship(type = "guanXi", direction = Relationship.Direction.INCOMING)
    private List<TxjmEdgeEntity> edgeIncoming;
    @Relationship(type = "guanXi", direction = Relationship.Direction.OUTGOING)
    private List<TxjmEdgeEntity> edgeOutgoing;

}