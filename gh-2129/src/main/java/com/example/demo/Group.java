package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@Node
@Getter
@Setter
public class Group {

    @Id
    @GeneratedValue
    private Long node_id;

    private Long groupId;

    private String name;

    private String[] tags;

    private String description;

    @Relationship(type = "IS_LINKED")
    private Set<Device> devices = new LinkedHashSet<>();

    @Relationship(type = "GROUP_LINK")
    private Set<Group> groups = new LinkedHashSet<>();

    @Override
    public String toString() {
        return "Group{" +
                "node_id=" + node_id +
                ", groupId=" + groupId +
                ", name='" + name + '\'' +
                ", groupType=" + Arrays.toString(tags) +
                ", description='" + description + '\'' +
                '}';
    }
}