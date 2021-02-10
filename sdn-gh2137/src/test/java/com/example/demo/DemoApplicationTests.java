package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataNeo4jTest
class DemoApplicationTests {

	@BeforeEach
	void setup(@Autowired Driver driver) {

		try (Session session = driver.session()) {
			session.run("MATCH (n) DETACH DELETE n");
			session.run("CREATE (:Country{code: 'DE'})-[:HAS_CURRENCY]->(:Currency{name: 'EUR'})").consume();
		}

	}

	@Test
	void loadByCode(@Autowired CountryRepository repository) {
		Optional<Country> de = repository.findByCode("DE");
		assertThat(de).isPresent();
		assertThat(de.get().getCurrencies().iterator().next().getName()).isEqualTo("EUR");
	}

}
