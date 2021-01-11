package com.meistermeier.neo4j.gh2104;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Gh2104ApplicationTests {

	private final ClientRepository repository;

	@Autowired
	Gh2104ApplicationTests(ClientRepository repository) {
		this.repository = repository;
	}

	@Test
	void saveAndFindByCode() {
		ClientNode node = new ClientNode();
		node.setCode("1234");
		repository.save(node);

		Optional<ClientNode> loadedNode = repository.findByCode("1234");
		assertThat(loadedNode).isPresent();
	}

}
