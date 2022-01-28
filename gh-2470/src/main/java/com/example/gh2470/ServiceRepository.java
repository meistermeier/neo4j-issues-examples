package com.example.gh2470;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface ServiceRepository extends Neo4jRepository<ServiceEntity, Long> {

    @Query("match p=(s:Service) where s.name IN $iterable return p " +
            "union match p=(:Service)-[:HAS_OPTION]->(option:ServiceOption) " +
            "where option.name IN $iterable return p")
    List<ServiceEntity> findAllByServiceOrServiceOptionId(Iterable<String> iterable);
}
