package com.meistermeier.gh2975;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Node
public class BaseNode {
    @Id
    @GeneratedValue
    private String id;

    @Relationship(direction = Relationship.Direction.OUTGOING)
    private Map<String, List<BaseRelationship>> relationships = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, List<BaseRelationship>> getRelationships() {
        return relationships;
    }

    public void setRelationships(Map<String, List<BaseRelationship>> relationships) {
        this.relationships = relationships;
    }
}
