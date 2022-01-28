package com.example.gh2470;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class ServiceOption {

    @Id
    public String name;

    @Override
    public String toString() {
        return "ServiceOption{" +
                "name='" + name + '\'' +
                '}';
    }
}
