package com.example.gh2583;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TxjmNodeEntityRepository extends Neo4jRepository<TxjmNodeEntity, Long> {

    // **It is normal**
    // **Neo4jRepository query**
    Page<TxjmNodeEntity> findAll(Pageable pageable);

    // **It is error, when query more than 20+ nodes data**
    // **custom query**
    @Query(value = """
        MATCH (s:hktxjm)
        WITH s OPTIONAL MATCH (s)-[r:guanXi]->(t:hktxjm)
        RETURN s, collect(r), collect(t)
        :#{orderBy(#pageable)} SKIP $skip LIMIT $limit
        """,
        countQuery = "MATCH (s:hktxjm) RETURN count(s)")
//    @Query(value = """
//            MATCH p=(s:hktxjm)-[r:guanXi]->(t:hktxjm)
//            RETURN s, relationships(p), nodes(p)
//            :#{orderBy(#pageable)} SKIP $skip LIMIT $limit
//            """,
//            countQuery = "MATCH (s:hktxjm) RETURN count(s)")
    Page<TxjmNodeEntity> findAllWithOutgoingEdge(Pageable pageable);
}