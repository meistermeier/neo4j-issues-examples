package com.example.gh2372;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.core.Neo4jTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Gh2372ApplicationTests {

    private final CarRepository carRepository;

    private final Neo4jTemplate neo4jTemplate;

    private final Driver driver;

    @Autowired
    Gh2372ApplicationTests(CarRepository carRepository, Neo4jTemplate neo4jTemplate, Driver driver) {
        this.carRepository = carRepository;
        this.neo4jTemplate = neo4jTemplate;
        this.driver = driver;
    }

    @BeforeEach
    void prepareData() {
        try (var session = driver.session()) {
            session.run("MATCH (n) DETACH DELETE n");

            session.run("CREATE (:Small:Car:Vehicle {uuid: 'SC', name: 'My small car'})").consume();
            session.run("CREATE (:OrdinaryCar:Car:Vehicle {uuid: 'OC', name: 'My ordinary car'})").consume();
            session.run("CREATE (:BigCar:Car:Vehicle {uuid: 'BC', name: 'My big car'})").consume();

        }
    }

    @Test
    void loadAllWithTemplate() {

        List<Domain.ACar> cars = neo4jTemplate.findAll(Domain.ACar.class);

        assertThat(cars).hasSize(3);
    }

    @Test
    void loadWithRepository() {

        Domain.ACar ordinaryCar = carRepository.findByUuid("OC");
        assertThat(ordinaryCar).isNotNull();
    }

}
