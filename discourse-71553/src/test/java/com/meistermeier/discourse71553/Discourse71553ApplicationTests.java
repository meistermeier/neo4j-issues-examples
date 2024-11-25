package com.meistermeier.discourse71553;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.neo4j.driver.types.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class Discourse71553ApplicationTests {

	private final static LocalDateTime NOW = LocalDateTime.now();

	@BeforeEach
	void cleanUpDatabase(@Autowired Driver driver) {
		driver.executableQuery("MATCH (n) detach delete n").execute();
	}

	@Test
	void createEntity(@Autowired Driver driver, @Autowired UserRepository repository) {
		repository.save(new User("meistermeier"));
//		repository.save(new User("myId1", "meistermeier"));

		var records = driver.executableQuery("MATCH (u:User) return u").execute().records();
		assertThat(records).hasSize(1);
		Map<String, Object> u = records.get(0).get("u").asNode().asMap();
		System.out.println(u);
		assertThat(u)
			.hasEntrySatisfying("id", value -> assertThat(value).isNotNull())
			.hasFieldOrPropertyWithValue("name", "meistermeier")
			.hasEntrySatisfying("createdAt", (createdAt) -> assertThat(((LocalDateTime) createdAt)).isAfter(NOW))
			.hasEntrySatisfying("modifiedAt", (modifiedAt) -> assertThat(((LocalDateTime) modifiedAt)).isAfter(NOW))
			.hasFieldOrPropertyWithValue("createdBy", "me")
			.hasFieldOrPropertyWithValue("modifiedBy", "me");

		// re-save the entry to update the modified data
		repository.save(repository.findByName("meistermeier"));
		// just to be save that we use the fresh copy from the database
		var updatedRecords = driver.executableQuery("MATCH (u:User) return u").execute().records();
		assertThat(updatedRecords).hasSize(1);
		System.out.println(updatedRecords.get(0).get("u").asNode().asMap());
		Node uu = updatedRecords.get(0).get("u").asNode();
		var createdAtValue = uu.get("createdAt").asLocalDateTime();
		var modifiedAtValue = uu.get("modifiedAt").asLocalDateTime();
		assertThat(modifiedAtValue).isAfter(createdAtValue);
	}

}
