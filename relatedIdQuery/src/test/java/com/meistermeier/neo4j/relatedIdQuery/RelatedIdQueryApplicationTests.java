package com.meistermeier.neo4j.relatedIdQuery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RelatedIdQueryApplicationTests {

	@Test
	void findByRelatedPersonId(@Autowired SidebarRepository sidebarRepository,
							   @Autowired PersonRepository personRepository) {

		Person person = new Person();
		person.setFirstname("Dieter");
		person.setLastname("Zufall");

		person = personRepository.save(person);

		SidebarBox sidebarBox = new SidebarBox("Test", person);

		sidebarRepository.save(sidebarBox);
		List<SidebarBox> b = sidebarRepository.findAllByPerson_Id(person.getId());

		assertThat(b).hasSize(1);
		assertThat(b.get(0).getTitle()).isEqualTo("Test");
		assertThat(b.get(0).getPerson())
				.extracting("firstname", "lastname")
				.contains("Dieter")
				.contains("Zufall");


	}

}
