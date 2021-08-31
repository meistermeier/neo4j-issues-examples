package com.example.so68938823;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.util.Collections;

@SpringBootTest
@EnableNeo4jRepositories
public class ProjectionTest {

    private final Neo4jTemplate neo4jTemplate;
    private final Driver driver;

    @Autowired
    public ProjectionTest(Neo4jTemplate neo4jTemplate, Driver driver) {
        this.neo4jTemplate = neo4jTemplate;
        this.driver = driver;
    }

    interface DATspaceProjection {
        // String getTitle(); // would be preferable
        String getSUPtitle();
        String getSUPid();
    }

    @BeforeEach
    void prepareData() {
        try (var session = driver.session()) {
            session.run("MATCH (n) detach delete n");
            session.run("CREATE (:DATspace{SUPid:'SPC_ML7', SUPtitle: 'blubb'})");
        }
    }

    @Test
    public void test_projection(){
        DatSpace d = neo4jTemplate.findOne("MATCH (s:DATspace {SUPid: $id}) RETURN s", Collections.singletonMap("id", "SPC_ML7"), DatSpace.class).get();

        d.setTitle("title modified");
        d.setSUPid("SUPid modified");

        DATspaceProjection p = neo4jTemplate.saveAs(d, DATspaceProjection.class);
    }
}