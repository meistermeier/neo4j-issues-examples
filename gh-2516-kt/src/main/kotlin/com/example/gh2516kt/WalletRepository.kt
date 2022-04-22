package com.example.gh2516kt

import org.springframework.data.neo4j.repository.Neo4jRepository

interface WalletTransactionRepository : Neo4jRepository<WalletTransaction?, String?>
