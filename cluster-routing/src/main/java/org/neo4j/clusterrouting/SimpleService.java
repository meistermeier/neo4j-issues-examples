package org.neo4j.clusterrouting;

import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.core.PreparedQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author Gerrit Meier
 */
@Service
public class SimpleService {

    final Neo4jTemplate neo4jTemplate;
    private final SomeRepository someRepository;

    public SimpleService(Neo4jTemplate neo4jTemplate, SomeRepository someRepository) {
        this.neo4jTemplate = neo4jTemplate;
        this.someRepository = someRepository;
    }

    @Transactional(readOnly = true)
    public void readSomeDataWithReadOnlyTx() {
        System.out.println(this.neo4jTemplate.findAll("MATCH (n:User) return n", User.class));
    }
    @Transactional()
    public void readSomeDataWithReadWriteTx() {
        System.out.println(this.neo4jTemplate.findAll("MATCH (n:User) return n", User.class));
    }

    @Transactional(readOnly = true)
    public void readWithMultipleOperations() {
        System.out.println(this.neo4jTemplate.toExecutableQuery(PreparedQuery.queryFor(Long.class).withCypherQuery("MATCH (u:User) return count(u)").withParameters(Map.of()).build()).getRequiredSingleResult());
        System.out.println(this.neo4jTemplate.findAll("MATCH (n:User) return n", User.class));
    }

    @Transactional
    public void nestedTransactionWithWriteTransaction() {
        someRepository.readOnly();
    }

    @Transactional(readOnly = true)
    public void nestedTransactionWithReadOnlyTransaction() {
        someRepository.readOnly();
    }

}
