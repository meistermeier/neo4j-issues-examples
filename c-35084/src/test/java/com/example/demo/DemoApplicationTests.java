package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationTests {

	private final Driver driver;

	DemoApplicationTests(@Autowired Driver driver) {
		this.driver = driver;
	}

	@BeforeEach
	void createDataset() {
		try (var session = driver.session()) {
			session.run("MATCH (n) detach delete n").consume();
			session.run("CREATE (:Entity{someProperty:'test'})-[:some_rel]->(:Entity2)");
		}
	}

	@Test
	void shouldLoadProjectionWithPageable(@Autowired EntityRepository repository) {
		Page<MyProjection> result = repository.findAllProjectedBy(PageRequest.of(0, 20));
		assertThat(result).hasSize(1);
	}

	@Test
	void shouldLoadProjectionByProperty(@Autowired EntityRepository repository) {
		Optional<MyProjection> result = repository.findBySomeProperty("test");
		assertThat(result)
				.isPresent().get()
				.extracting("someProperty").isEqualTo("test");
	}

}
