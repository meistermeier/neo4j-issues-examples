package org.example.gh2885;

import org.neo4j.cypherdsl.core.Cypher;
import org.neo4j.cypherdsl.core.Functions;
import org.neo4j.cypherdsl.core.Node;
import org.neo4j.cypherdsl.core.Relationship;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;
import java.util.Map;

import static org.neo4j.cypherdsl.core.Cypher.match;
import static org.neo4j.cypherdsl.core.Cypher.parameter;
import static org.neo4j.cypherdsl.core.Functions.collect;

/**
 * @author Gerrit Meier
 */
public class Playground {

	private Neo4jTemplate neo4jTemplate;

	record Movie(@Id String title, String tagline) {
	}

	record Person(@Id String name, @org.springframework.data.neo4j.core.schema.Relationship("ACTED_IN") List<Movie> movies) {
	}

	interface PersonRepository extends Neo4jRepository<Person, String> {
		Person findByName(String name);
	}

Person findPersonByName() {
	Node personNode = Cypher.node("Person").named("p");
	Node movieNode = Cypher.node("Movie").named("m");
	Relationship actedInRelationship = personNode.relationshipTo(movieNode, "ACTED_IN");
	return neo4jTemplate.findOne(
			match(actedInRelationship)
					.where(personNode.property("name").eq(parameter("actorName")))
					.returning(
							personNode.getRequiredSymbolicName(),
							collect(actedInRelationship),
							collect(movieNode)
					).build(),
					Map.of("actorName", "Laurence Fishburne"),
					Person.class)
			.get();
}
}
