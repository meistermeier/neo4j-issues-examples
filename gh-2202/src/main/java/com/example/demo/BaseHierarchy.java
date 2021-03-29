package com.example.demo;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Node
public class BaseHierarchy {

    @Id
    @GeneratedValue
    private Long id;

    private final String code;

    @Relationship("INCLUDES")
    private Set<Group> groups;

    public BaseHierarchy(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public Set<Group> getGroups() {
        return groups;
    }
}
