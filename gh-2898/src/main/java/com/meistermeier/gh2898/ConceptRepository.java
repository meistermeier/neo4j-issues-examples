package com.meistermeier.gh2898;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Gerrit Meier
 */
public interface ConceptRepository extends Neo4jRepository<Concept, Long> {

	@Query("MATCH (c:Concept)-[r]-(related:Concept) RETURN collect({id:ID(r),relation:type(r),relatedConcept:related})")
	List<CCLink> test();
}
