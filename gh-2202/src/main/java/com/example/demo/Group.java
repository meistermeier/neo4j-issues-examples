package com.example.demo;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Node
public class Group {

    @Id
    @GeneratedValue
    private Long id;

    private final String code;

    @Relationship("PARENT_OF")
    private Set<Client> clients;

    public Group(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public Set<Client> getClients() {
        return clients;
    }
}
