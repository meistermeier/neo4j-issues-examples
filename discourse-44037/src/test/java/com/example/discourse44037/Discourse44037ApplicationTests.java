package com.example.discourse44037;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Discourse44037ApplicationTests {

    private final CocktailRepository repository;

    private final Driver driver;

    @Autowired
    Discourse44037ApplicationTests(CocktailRepository repository, Driver driver) {
        this.repository = repository;
        this.driver = driver;
    }

    @BeforeEach
    void prepareData() {
        try (var session = driver.session()) {
            session.run("MATCH (n) detach delete n");
            session.run("CREATE (c:Cocktail{name:'YummyYum'})-[:DESCRIPTION{value:'Do something'}]->(s:Step{number:1})");
        }
    }

    @Test
    void loadWithRelationshipProperties() {
        List<CocktailEntity> cocktails = repository.findAll();
        assertThat(cocktails).hasSize(1);

        CocktailEntity cocktail = cocktails.get(0);
        assertThat(cocktail.getName()).isEqualTo("YummyYum");
        assertThat(cocktail.getSteps()).hasSize(1);

        StepDescEntity stepDesc = cocktail.getSteps().iterator().next();

        assertThat(stepDesc.getValue()).isEqualTo("Do something");
        StepEntity step = stepDesc.getStepEntity();
        assertThat(step.getNumber()).isEqualTo(1);

    }

}
