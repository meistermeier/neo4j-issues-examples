package com.example.demo;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface BaseHierarchyRepository extends Neo4jRepository<BaseHierarchy, Long> {

    @Query("MATCH path=(b:BaseHierarchy {code:'JQCLB00'})-[i:INCLUDES]->(g:Group)-[p:PARENT_OF*]->(c:Client) " +
            "WHERE (g.code = 'EMIRG' OR g.code = 'BAESS') " +
            "AND c.sector = '14' " +
            "WITH collect(path) as paths, b " +
            "WITH b, " +
            "reduce(a=[], node in reduce(b=[], c in [aa in paths | nodes(aa)] | b + c) | case when node in a then a else a + node end) as nodes, " +
            "reduce(d=[], relationship in reduce(e=[], f in [dd in paths | relationships(dd)] | e + f) | case when relationship in d then d else d + relationship end) as relationships " +
            "RETURN b, relationships, nodes")
    BaseHierarchy customQuery();

}
