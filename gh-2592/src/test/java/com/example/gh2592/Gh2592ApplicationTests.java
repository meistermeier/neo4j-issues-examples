package com.example.gh2592;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@SpringBootTest
class Gh2592ApplicationTests {

	@Autowired
	TxjmRepository repository;

	@Test
	void testIncomingEdgesSingle() {
		String model = "node-41498c56-7f82-4444-927a-39e73fc1bfa1";
		TxjmNodeEntity entityFromCustomQueryAll = repository.findByIdd(model);

		Assertions.assertThat(entityFromCustomQueryAll.getEdgeIncoming()).hasSize(2);

	}

	@Test
	void testIncomingEdgesAll() {
		var entities = repository.findAll();
		var entity = entities.stream().filter(s -> s.getIdd().equals("node-41498c56-7f82-4444-927a-39e73fc1bfa1")).findFirst().get();
		Assertions.assertThat(entity.getEdgeIncoming()).hasSize(2);
	}

	@Test
	void testIncomingEdgesCustomQuery() {
		String model = "node-41498c56-7f82-4444-927a-39e73fc1bfa1";
		Page<TxjmNodeEntity> entitiesForCustomQueryAll = repository.queryAllNodes(model, Pageable.ofSize(20));
		TxjmNodeEntity entityFromCustomQueryAll = entitiesForCustomQueryAll.stream().filter(s -> s.getIdd().equals("node-41498c56-7f82-4444-927a-39e73fc1bfa1")).findFirst().get();

		Assertions.assertThat(entityFromCustomQueryAll.getEdgeIncoming()).hasSize(2);
	}


}
