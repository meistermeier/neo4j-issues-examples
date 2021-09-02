package com.example.gh2372;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CarRepository extends Neo4jRepository<Domain.ACar, Long> {

    Domain.ACar findByUuid(String uuid);
}
