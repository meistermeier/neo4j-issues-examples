package com.example.springbootnext;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
public class User {

    @Id
    @GeneratedValue
    private final Long id;

    @Relationship("FRIEND")
    private final List<Friend> friends;

    public User(Long id, List<Friend> friends) {
        this.id = id;
        this.friends = friends;
    }

    public Long getId() {
        return id;
    }

    public List<Friend> getFriends() {
        return friends;
    }

}
