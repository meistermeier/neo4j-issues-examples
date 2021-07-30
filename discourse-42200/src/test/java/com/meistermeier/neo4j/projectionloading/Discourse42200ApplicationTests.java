package com.meistermeier.neo4j.projectionloading;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Discourse42200ApplicationTests {

    private final Driver driver;
    private final ItemRepository repository;

    @Autowired
    Discourse42200ApplicationTests(Driver driver, ItemRepository repository) {
        this.driver = driver;
        this.repository = repository;
    }

    @BeforeEach
    void setupDatabase() {
        try (var session = driver.session()) {
            session.run("MATCH (n) DETACH DELETE n");

            session.run("CREATE (:Item{firstField:'somethingEnglish', secondField:'somethingRussian'})");
            session.run("CREATE (:Item{secondField:'somethingRussian2'})");
            session.run("CREATE (:Item{firstField:'somethingEnglish2'})");
        }
    }

    @Test
    void findItemProjections() {
        List<ItemSubset> items = repository.findWithKeywords("somethingenglish", "somethingRussian");
        assertThat(items).hasSize(3);
    }

}
