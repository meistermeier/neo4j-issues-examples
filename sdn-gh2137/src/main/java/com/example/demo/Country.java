package com.example.demo;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
public class Country {
    @Id
    private String code;

    @Relationship(type = "HAS_CURRENCY", direction = Relationship.Direction.OUTGOING)
    private Set<Currency> currencies = new HashSet<>();

    public String getCode() {
        return code;
    }

    public Set<Currency> getCurrencies() {
        return currencies;
    }
}
