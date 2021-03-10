package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.LinkedHashSet;
import java.util.Set;

@Node
@Getter
@Setter
public class Device {

    @Id
    @GeneratedValue
    private Long node_id;

    private Long deviceId;

    private String name;

    @Relationship(type = "IS_LINKED", direction = Relationship.Direction.INCOMING)
    private Set<Group> groups = new LinkedHashSet<>();

    @Override
    public String toString() {
        return "Device{" +
                "node_id=" + node_id +
                ", deviceId=" + deviceId +
                ", name='" + name + '\'' +
                '}';
    }
}