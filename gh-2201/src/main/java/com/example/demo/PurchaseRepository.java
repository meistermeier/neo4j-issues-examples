package com.example.demo;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PurchaseRepository extends Neo4jRepository<PurchaseEntity, Long> {
}
