package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface EntityRepository extends Neo4jRepository<Entity, Long> {
    Optional<MyProjection> findBySomeProperty(String someProperty);
    Page<MyProjection> findAllProjectedBy(Pageable pageable);
}
