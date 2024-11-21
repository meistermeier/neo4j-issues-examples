package com.meistermeier.gh2952;

import com.querydsl.core.annotations.QueryEntity;
import jakarta.persistence.Entity;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * @author Gerrit Meier
 */
@Node
@Entity
@QueryEntity
public class User {

    @Id
    private final String name;

    private final String description;

    public User(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
