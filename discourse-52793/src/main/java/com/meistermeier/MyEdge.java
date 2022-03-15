package com.meistermeier;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class MyEdge {

    @Id @GeneratedValue
    Long id;

    String idNodeFrom;
    String idNodeTo;

    @TargetNode
    MyNode targetNode;

    public MyEdge(String idNodeFrom, String idNodeTo, MyNode targetNode) {
//        this.id = idNodeFrom + " -> " + idNodeTo;
        this.idNodeFrom = idNodeFrom;
        this.idNodeTo = idNodeTo;
        this.targetNode = targetNode;
    }
    
    // Getters and setters
}
