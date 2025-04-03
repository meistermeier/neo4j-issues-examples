package com.meistermeier.gh2975;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.data.neo4j.core.Neo4jTemplate;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ImportTestcontainers(Neo4jContainerConfiguration.class)
class Gh2975ApplicationTests {

	@Test
	void persistNewPersonsWithAddresses(@Autowired Neo4jTemplate neo4jTemplate) {

		neo4jTemplate.findAll(Person.class);

		var p1 = new Person();
		p1.setName("p1");
		var a1 = new Address();
		a1.setAddressValue("a1");
		p1.setAddress(a1);

		var p2 = new Person();
		p2.setName("p2");
		var a2 = new Address();
		a2.setAddressValue("a2");
		p2.setAddress(a2);

		var persons = List.of(p1, p2);

		neo4jTemplate.saveAll(persons);
	}

@Test
void testTypeMapping(@Autowired BaseNodeRepository baseNodeRepository) {
	var node = new BaseNode();
	RelationshipA e1 = new RelationshipA();
	e1.setTargetNode(new BaseNode());
	RelationshipB e2 = new RelationshipB();
	e2.setTargetNode(new BaseNode());
	node.setRelationships(Map.of(
		"relation", List.of(
			e1, e2
		)
	));
	var persistedNode = baseNodeRepository.save(node);

	var loadedNode = baseNodeRepository.findById(persistedNode.getId()).get();
	List<BaseRelationship> relationships = loadedNode.getRelationships().get("relation");
	assertThat(relationships).hasExactlyElementsOfTypes(RelationshipA.class, RelationshipB.class);
}

}
