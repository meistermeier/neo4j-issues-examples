package org.neo4j.clusterrouting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gerrit Meier
 */
@Repository
public class SomeRepository {

    final Neo4jTemplate neo4jTemplate;

    public SomeRepository(Neo4jTemplate neo4jTemplate) {
        this.neo4jTemplate = neo4jTemplate;
    }

    @Transactional(readOnly=true)
    public void readOnly() {
        System.out.println(neo4jTemplate.findAll("MATCH (n:User) return n", User.class));
    }
}
