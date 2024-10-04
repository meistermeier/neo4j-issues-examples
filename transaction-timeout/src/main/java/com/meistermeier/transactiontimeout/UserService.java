package com.meistermeier.transactiontimeout;

import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gerrit Meier
 */
@Service
public class UserService {

    private final Neo4jClient neo4jClient;

    public UserService(Neo4jClient neo4jClient) {
        this.neo4jClient = neo4jClient;
    }

    @Transactional(timeout = 1)
    void createLotsOfUsersWithAnnotationTimeout() {
        neo4jClient.query("UNWIND range(1,1000000) as thing CREATE (u:User) set u.name = toString(thing) WITH thing, u MATCH (b:User) where b.name = toString(thing-1) MERGE (u)-[:KNOWS]->(b)")
            .run();
    }

    @Transactional
    void createLotsOfUsersWithoutAnnotationTimeout() {
        neo4jClient.query("UNWIND range(1,1000000) as thing CREATE (u:User) set u.name = toString(thing) WITH thing, u MATCH (b:User) where b.name = toString(thing-1) MERGE (u)-[:KNOWS]->(b)")
            .run();
    }}
