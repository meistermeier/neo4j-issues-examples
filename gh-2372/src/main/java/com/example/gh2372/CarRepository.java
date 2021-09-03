package com.example.gh2372;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface CarRepository extends Neo4jRepository<Domain.Vehicle, String> {

    Domain.Vehicle findByUuid(String uuid);

    List<Domain.Vehicle> findByArchivedIsNullOrArchivedIsFalse();

    long countByUuidContaining(String uuidPart);
}
