package com.example.gh2470;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node("Service")
public class ServiceEntity {

    @Id
    @GeneratedValue
    public Long id;

    public String name;

    @Relationship("HAS_OPTION")
    public List<ServiceOption> serviceOptions;

    @Override
    public String toString() {
        return "ServiceEntity{" +
                "name='" + name + '\'' +
                ", serviceOptions=" + serviceOptions +
                '}';
    }
}
