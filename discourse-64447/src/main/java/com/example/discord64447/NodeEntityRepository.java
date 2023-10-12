package com.example.discord64447;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Gerrit Meier
 */
public interface NodeEntityRepository extends Neo4jRepository<NodeEntity, String> {

		@Query("MATCH (root:RootNode)-->(ln:Node)-[r*]->(rn:Node) WHERE root.rootNodeName = $rootNodeName and ln.nodeId = $nodeId"
//				+ "and (CASE WHEN {depth} IS NULL THEN length(r) > 0 ELSE length(r) <= {depth} END)"
				+ " return ln,collect(r),collect(rn)")
		NodeEntity nodeEntityQuery(@Param("rootNodeName") String rootNodeName, @Param("nodeId") String nodeId);

}
