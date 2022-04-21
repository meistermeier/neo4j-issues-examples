package com.example.gh2516;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface WalletTransactionRepository extends Neo4jRepository<WalletTransaction, String> {
}
