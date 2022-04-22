package com.example.gh2516kt

import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship
import java.math.BigDecimal
import java.time.Instant

@Node("Deposit")
class DepositTransaction(
    id: String? = null,
    value: BigDecimal? = null,
    walletTransactionStatus: List<TransactionStatusUpdate>? = null,
    balance: WalletBalance? = null,
    createdAt: Long? = Instant.now().toEpochMilli(),
    updatedAt: Long? = null,

    @Relationship("FROM")
    val from: Wallet? = null,

    @Relationship("TO")
    val to: Wallet? = null,
) : WalletTransaction(id, value, walletTransactionStatus, balance, createdAt, updatedAt)
