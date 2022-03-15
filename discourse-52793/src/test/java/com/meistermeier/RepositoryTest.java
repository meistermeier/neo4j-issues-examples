package com.meistermeier;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Gerrit Meier
 */
@SpringBootTest
public class RepositoryTest {

	private final MyNodeRepository repository;

	@Autowired
	public RepositoryTest(MyNodeRepository repository) {
		this.repository = repository;
	}

	@Test
	void findAll() {
		assertThat(repository.findAll()).hasSize(631);
	}

	@Test
	void findWithCustomQuery() {
		Optional<MyNode> myNode = repository.customSubGraphStartingAt("2");

		assertThat(myNode).isPresent();
		assertThat(myNode.get().outgoingEdges).hasSize(2);
	}

	@Test
	void findWithAlternativeCustomQuery() {
		Optional<MyNode> myNode = repository.alternativeVersion("2");

		assertThat(myNode).isPresent();
		assertThat(myNode.get().outgoingEdges).hasSize(2);
	}
}
