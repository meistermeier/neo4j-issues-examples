package org.example.gh2885;

import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.UUID;

@RelationshipProperties
public class Neo4jMacroTaskCommand implements Comparable<Neo4jMacroTaskCommand> {

    @RelationshipId
    private String id;

    private int index;

    @TargetNode
    private Neo4jImplementation command;

    public Neo4jMacroTaskCommand(final int index, final Neo4jImplementation command) {
//        this.id = UUID.randomUUID().toString();
        this.index = index;
        this.command = command;
    }
    
    public int getIndex() {
        return index;
    }

    @Override
    public int compareTo(final Neo4jMacroTaskCommand object) {
        return Integer.compare(index, object.getIndex());
    }
}