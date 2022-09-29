package com.example.gh2592;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TxjmRepository extends Neo4jRepository<TxjmNodeEntity, Long> {

	TxjmNodeEntity findByIdd(String iid);

	@Transactional(readOnly = true)
	@Query(value = """
			MATCH (s:hktxjm)
			WHERE s.idd = $code AND s.nodeStatu = 'ACTIVE'
			WITH s :#{orderBy(#pageable)} SKIP $skip LIMIT $limit
			optional MATCH (s)-[r]-(t:hktxjm)
			WHERE type(r) in ['guanXi']
			RETURN s,collect(r), collect(t)
			""",
			countQuery = """
					MATCH (s:hktxjm)
					WHERE s.idd = $code AND s.nodeStatu = 'ACTIVE'
					RETURN count(s)
					"""
	)
	Page<TxjmNodeEntity> queryAllNodes(String code, Pageable pageable);


}
