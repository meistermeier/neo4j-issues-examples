package com.example.demo;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Node
public class Client {

    @Id
    @GeneratedValue
    private Long id;

    private final String sector;

    @Relationship("PARENT_OF")
    private Set<Client> clients;

    public Client(String sector) {
        this.sector = sector;
    }

    public String getSector() {
        return sector;
    }

    public Set<Client> getClients() {
        return clients;
    }
}
