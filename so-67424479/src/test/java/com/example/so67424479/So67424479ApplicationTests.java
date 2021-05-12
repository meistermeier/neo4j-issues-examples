package com.example.so67424479;

import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.neo4j.driver.types.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class So67424479ApplicationTests {

    private final MyRepository repository;
    private final Driver driver;

    @Autowired
    So67424479ApplicationTests(MyRepository repository, Driver driver) {
        this.repository = repository;
        this.driver = driver;
    }

    @Test
    void usingCallback() {

        try (var session = driver.session()) {
            session.run("MATCH (n:MyEntity) detach delete n").consume();
        }

        repository.save(new MyEntity("TestName"));

        try (var session = driver.session()) {
            Node myEntity = session.run("MATCH (n:MyEntity) return n").single().get("n").asNode();
            assertThat(myEntity.get("name").asString()).isEqualTo("AnotherName");
        }


    }

}
