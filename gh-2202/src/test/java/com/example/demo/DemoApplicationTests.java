package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.core.Neo4jTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationTests {

	private final Driver driver;
	private final Neo4jTemplate neo4jTemplate;
	private final BaseHierarchyRepository repository;

	DemoApplicationTests(
			@Autowired Driver driver,
			@Autowired Neo4jTemplate neo4jTemplate,
			@Autowired BaseHierarchyRepository repository) {

		this.driver = driver;
		this.neo4jTemplate = neo4jTemplate;
		this.repository = repository;
	}

	@BeforeEach
	void setupData() {
		try (var session = driver.session()) {
			Transaction transaction = session.beginTransaction();
			transaction.run("MATCH (n) DETACH DELETE n");
			transaction.run(
					"CREATE (b:BaseHierarchy{code:'JQCLB00'}) " +
					"CREATE (b)-[:INCLUDES]->(g1:Group{code:'EMIRG'}) " +
					"CREATE (b)-[:INCLUDES]->(g2:Group{code:'BAESS'}) " +
					"CREATE (g1)-[:PARENT_OF]->(c1:Client{sector:'11'}) " +
					"CREATE (g2)-[:PARENT_OF]->(c2:Client{sector:'11'}) " +
					"CREATE (c1)-[:PARENT_OF]->(:Client{sector:'14'}) " +
					"CREATE (c2)-[:PARENT_OF]->(:Client{sector:'14'}) "
			);
			transaction.commit();
		}
	}

	@Test
	void loadWithRepositoryCustomQuery() {
		BaseHierarchy baseHierarchy = repository.customQuery();
		assertThat(baseHierarchy.getCode()).isEqualTo("JQCLB00");
		assertThat(baseHierarchy.getGroups()).extracting("code").containsExactlyInAnyOrder("EMIRG", "BAESS");
		assertThat(baseHierarchy.getGroups())
				.flatExtracting("clients")
				.extracting("sector").containsExactly("11", "11");
		assertThat(baseHierarchy.getGroups())
				.flatExtracting("clients").flatExtracting("clients")
				.extracting("sector").containsExactly("14", "14");
	}

	@Test
	void loadWithTemplate() {

		String query = "MATCH path=(b:BaseHierarchy {code:'JQCLB00'})-[i:INCLUDES]->(g:Group)-[p:PARENT_OF*]->(c:Client) " +
				"WHERE (g.code = 'EMIRG' OR g.code = 'BAESS') " +
				"AND c.sector = '14' " +
				"WITH collect(path) as paths, b " +
				"WITH b, " +
				"reduce(a=[], node in reduce(b=[], c in [aa in paths | nodes(aa)] | b + c) | case when node in a then a else a + node end) as nodes, " +
				"reduce(d=[], relationship in reduce(e=[], f in [dd in paths | relationships(dd)] | e + f) | case when relationship in d then d else d + relationship end) as relationships " +
				"RETURN b, relationships, nodes";

		BaseHierarchy baseHierarchy = neo4jTemplate.findAll(query, BaseHierarchy.class).get(0);
		assertThat(baseHierarchy.getCode()).isEqualTo("JQCLB00");
		assertThat(baseHierarchy.getGroups()).extracting("code").containsExactlyInAnyOrder("EMIRG", "BAESS");
		assertThat(baseHierarchy.getGroups())
				.flatExtracting("clients")
				.extracting("sector").containsExactly("11", "11");
		assertThat(baseHierarchy.getGroups())
				.flatExtracting("clients").flatExtracting("clients")
				.extracting("sector").containsExactly("14", "14");
	}

}
