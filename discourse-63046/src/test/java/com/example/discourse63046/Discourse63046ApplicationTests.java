package com.example.discourse63046;

import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Discourse63046ApplicationTests {

	@Autowired
	FamilyRepository repository;

	@Autowired
	Driver driver;

	@Test
	void contextLoads() {
		Long familyId = null;
		try (var session = driver.session()) {
			session.run("MATCH (n) detach delete n").consume();
			familyId = session.run("""
					CREATE (f:Family{name:'myFamily'})
					CREATE (cat:Cat:Pet{name:'mauzi'})-[:SLEEP_SPOT]->(s:SleepSpot{name:'hidyhidy', location:'blanket'})
					CREATE (dog:Dog:Pet{name:'wauwau'})-[:CHEW_TOY]->(c:ChewToy{name:'bitybity', usedState:7})
					MERGE (f)-[:PET]->(cat)
					MERGE (f)-[:PET]->(dog)
					RETURN id(f)""").single().get("id(f)").asLong();
		}

		Family family = repository.findById(familyId).get();

		assertThat(family.name).isEqualTo("myFamily");
		assertThat(family.pets).hasSize(2)
				.satisfies(pet -> {
					if (pet instanceof Cat cat) {
						assertThat(cat.name).isEqualTo("mauzi");
						assertThat(cat.sleepSpots.get(0).name).isEqualTo("hidyhidy");
						assertThat(cat.sleepSpots.get(0).location).isEqualTo("blanket");
					} else if (pet instanceof Dog dog) {
						assertThat(dog.name).isEqualTo("wauwau");
						assertThat(dog.chewToys.get(0).name).isEqualTo("bitybity");
						assertThat(dog.chewToys.get(0).usedState).isEqualTo(7);
					}
				});
	}

}
