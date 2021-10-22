package com.example.so69577888;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface OrderRepository extends Neo4jRepository<Order, String> {
}
