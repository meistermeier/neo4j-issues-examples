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

            session.run("CREATE (:SmallCar:Car:Vehicle {uuid: 'SC', name: 'My small car', propertyOfSmallCar: 'sc property', generalPropertyOfTheCar: 'general sc property'})").consume();
            session.run("CREATE (:Car:Vehicle {uuid: 'OC', name: 'My ordinary car', generalPropertyOfTheCar: 'general oc property'})").consume();
            session.run("CREATE (:BigCar:Car:Vehicle {uuid: 'BC', name: 'My big car', propertyOfBigCar: 'bc property', generalPropertyOfTheCar: 'general bc property'})").consume();

        }
    }

    @Test
    void loadAllWithTemplate() {

        List<Domain.Vehicle> cars = neo4jTemplate.findAll(Domain.Vehicle.class);

        assertThat(cars).hasSize(3);
    }

    @Test
    void loadByUuidWithTemplate() {

        Domain.Vehicle smallCar = neo4jTemplate.findById("SC", Domain.Vehicle.class).get();
        Domain.Vehicle ordinaryCar = neo4jTemplate.findById("OC", Domain.Vehicle.class).get();
        Domain.Vehicle bigCar = neo4jTemplate.findById("BC", Domain.Vehicle.class).get();

        assertThat(smallCar).isInstanceOf(Domain.SmallCar.class);
        assertThat(ordinaryCar).isInstanceOf(Domain.OrdinaryCar.class);
        assertThat(bigCar).isInstanceOf(Domain.BigCar.class);
    }

    @Test
    void loadByStringQueryWithTemplate() {

        // or use neo4j-cypherdsl https://github.com/neo4j-contrib/cypher-dsl/
        String query =
                "MATCH (car_0:Car:Vehicle) WHERE TRUE AND (NOT exists(car_0.archived) OR car_0.archived = false) "
              + "OPTIONAL MATCH (car_0)-[r:keepsTo]->(route:Route) "
              + "RETURN car_0, collect(r), collect(route)";

        List<Domain.Vehicle> vehicles = neo4jTemplate.findAll(query, Domain.Vehicle.class);

        assertThat(vehicles).hasAtLeastOneElementOfType(Domain.SmallCar.class);
        assertThat(vehicles).hasAtLeastOneElementOfType(Domain.OrdinaryCar.class);
        assertThat(vehicles).hasAtLeastOneElementOfType(Domain.BigCar.class);
    }

    @Test
    void loadWithRepository() {

        Domain.OrdinaryCar ordinaryCar = (Domain.OrdinaryCar) carRepository.findByUuid("OC");
        assertThat(ordinaryCar.generalPropertyOfTheCar).isEqualTo("general oc property");

        Domain.SmallCar smallCar = (Domain.SmallCar) carRepository.findByUuid("SC");
        assertThat(smallCar.generalPropertyOfTheCar).isEqualTo("general sc property");
        assertThat(smallCar.propertyOfSmallCar).isEqualTo("sc property");

        Domain.BigCar bigCar = (Domain.BigCar) carRepository.findByUuid("BC");
        assertThat(bigCar.generalPropertyOfTheCar).isEqualTo("general bc property");
        assertThat(bigCar.propertyOfBigCar).isEqualTo("bc property");
    }

    @Test
    void loadAllWithGeneratedArchivedBasedQuery() {
        // creates query `MATCH (n:`Vehicle`) WHERE (n.archived IS NULL OR n.archived = false)....`
        List<Domain.Vehicle> vehicles = carRepository.findByArchivedIsNullOrArchivedIsFalse();
        assertThat(vehicles).hasSize(3);
    }

    @Test
    void countByRepository() {
        assertThat(carRepository.countByUuidContaining("C")).isEqualTo(3);
    }

}
