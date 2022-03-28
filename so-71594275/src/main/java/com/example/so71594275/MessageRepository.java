package com.example.so71594275;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface MessageRepository extends Neo4jRepository<Message, UUID> {
}
