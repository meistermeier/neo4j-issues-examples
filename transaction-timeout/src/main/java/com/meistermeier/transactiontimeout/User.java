package com.meistermeier.transactiontimeout;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

/**
 * @author Gerrit Meier
 */
@Node
public class User {

    @Id
    @GeneratedValue(generatorClass = GeneratedValue.UUIDGenerator.class)
    String uuid;

    final String name;

    @Relationship("KNOWS")
    final List<User> users;

    public User(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    public List<User> getUsers() {
        return users;
    }
}
