package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.cypherdsl.core.Cypher;
import org.neo4j.cypherdsl.core.Expression;
import org.neo4j.cypherdsl.core.FunctionInvocation;
import org.neo4j.cypherdsl.core.Functions;
import org.neo4j.cypherdsl.core.NamedPath;
import org.neo4j.cypherdsl.core.Node;
import org.neo4j.cypherdsl.core.Statement;
import org.neo4j.cypherdsl.core.SymbolicName;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.core.Neo4jTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.neo4j.cypherdsl.core.Cypher.match;
import static org.neo4j.cypherdsl.core.Cypher.node;
import static org.neo4j.cypherdsl.core.Cypher.path;
import static org.neo4j.cypherdsl.core.Functions.collect;

import java.util.Map;
import java.util.function.Function;

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

	/**
	 * @param extractor Either a call to {@link Functions#nodes(NamedPath)} or {@link Functions#relationships(NamedPath)}
	 * @param alias the alias of the expression to be returned
	 * @return
	 */
	Expression extract(
		Function<SymbolicName, FunctionInvocation> extractor,
		String alias
	) {
		var paths = Cypher.name("paths");
		var target = Cypher.name("t");
		var pathElement = Cypher.name("pathElement");
		var innerTarget = Cypher.name("it");
		var innerElement = Cypher.name("ie");
		var element = Cypher.name("e");

		var innerReduction = Functions
			.reduce(innerElement).in(Cypher.listWith(pathElement).in(paths).returning(extractor.apply(pathElement)))
			.map(innerTarget.add(innerElement))
			.accumulateOn(innerTarget).withInitialValueOf(Cypher.listOf());
		return Functions
			.reduce(element).in(innerReduction)
			.map(Cypher.caseExpression().when(element.in(target)).then(target).elseDefault(target.add(element)))
			.accumulateOn(target).withInitialValueOf(Cypher.listOf())
			.as(alias);
	}

	@Test
	void loadWithTemplate() {

		var r = Cypher.name("r");
		var g = node("Group").named("g");
		var c = node("Client").named("c");
		var path = Cypher.name("p");

		Statement statement = match(
			path(path).definedBy(
				node("BaseHierarchy").named(r).withProperties("code", Cypher.parameter("code")).relationshipTo(g, "INCLUDES").relationshipTo(c, "PARENT_OF").unbounded()))
			.where(g.property("code").isEqualTo(Cypher.literalOf("EMIRG")).or(g.property("code").isEqualTo(Cypher.literalOf("BAESS"))))
			.and(c.property("sector").isEqualTo(Cypher.literalOf("14")))
			.with(r, collect(path).as("paths"))
			.returning(r, extract(Functions::nodes, "nodes"), extract(Functions::relationships, "relationships"))
			.build();

		BaseHierarchy baseHierarchy = neo4jTemplate.findAll(statement, Map.of("code", "JQCLB00"), BaseHierarchy.class).get(0);
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
