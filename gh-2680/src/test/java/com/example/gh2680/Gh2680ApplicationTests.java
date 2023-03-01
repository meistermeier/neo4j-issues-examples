package com.example.gh2680;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Gh2680ApplicationTests {

	Long materialId;

	@BeforeEach
	void setup(@Autowired Driver driver) {
		try (var session = driver.session()) {
			session.run("MATCH (m:Material) DELETE m").consume();
			materialId = session.run("CREATE (m:`材料`:Material{label: 'test'}) return id(m)").single().get("id(m)").asLong();
		}
	}

	@Test
	void shouldNotDuplicateNode(@Autowired MaterialRepository repository, @Autowired Driver driver) {
		Material oldMaterial = repository.findById(materialId).get();
		Material newMaterial = new Material();
		newMaterial.setMaterialId(materialId);
		newMaterial.setMaterialName("another name");
		newMaterial.setMaterialDescription("test description");

		oldMaterial.setMaterialName(newMaterial.getMaterialName());
		oldMaterial.setMaterialDescription(newMaterial.getMaterialDescription());

		repository.save(oldMaterial);

		try (var session = driver.session()) {
			var count = session.run("MATCH (m:Material) return count(m)").single().get("count(m)").asLong();
			assertThat(count).isEqualTo(1);
		}
	}

}
