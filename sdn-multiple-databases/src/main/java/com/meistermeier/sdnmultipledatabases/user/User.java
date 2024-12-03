package com.meistermeier.sdnmultipledatabases.user;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

/**
 * @author Gerrit Meier
 */
@Node
public class User {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    String uuid;

    private final String name;

    public User(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }
}
