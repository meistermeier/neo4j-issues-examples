package com.example.discourse45557;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.core.Neo4jTemplate;

import java.util.List;

@SpringBootTest
class Discourse45557ApplicationTests {

    private final Neo4jTemplate neoTemplate;
    private final Driver driver;

    @Autowired
    Discourse45557ApplicationTests(Neo4jTemplate neoTemplate, Driver driver) {
        this.neoTemplate = neoTemplate;
        this.driver = driver;
    }

    @BeforeEach
    void prepareData() {
        try (var session = driver.session()) {
            session.run("MATCH (n) DETACH DELETE n").consume();
        }
    }

    @Test
    void create_persons_calls() {
        Person p1 = new Person().withName("Person-1");
        Person p2 = new Person().withName("Person-2");
        p1.setCalls(List.of(new Calls(p2).withDay("monday")
                ,new Calls(p2).withDay("tuesday ")
                ,new Calls(p2).withDay("wednesday")
        ));
        this.neoTemplate.save(p1);
    }

}
