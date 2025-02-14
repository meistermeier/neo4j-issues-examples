package com.meistermeier.sdnmultipledatabases.user;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * @author Gerrit Meier
 */
@Node
public class Hobby {

    @Id
    public String id;

    public String name;
}
