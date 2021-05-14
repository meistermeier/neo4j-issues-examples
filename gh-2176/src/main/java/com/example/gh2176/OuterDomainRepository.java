package com.example.gh2176;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface OuterDomainRepository extends Neo4jRepository<OuterDomain, String> {
}
