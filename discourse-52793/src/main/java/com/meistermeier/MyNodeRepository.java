package com.meistermeier;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Gerrit Meier
 */
public interface MyNodeRepository extends Neo4jRepository<MyNode, String> {

	@Query("MATCH (root {id: $nodeId})-[r*]->(leaf) RETURN root, collect(r), collect(leaf)")
	Optional<MyNode> customSubGraphStartingAt(@Param("nodeId") String nodeId);

	@Query("MATCH p=(root {id: $nodeId})-[*]->() RETURN root, collect(nodes(p)), collect(relationships(p))")
	Optional<MyNode> alternativeVersion(@Param("nodeId") String nodeId);
}
