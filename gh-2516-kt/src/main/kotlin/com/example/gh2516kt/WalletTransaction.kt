package com.example.gh2516kt

import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship
import java.math.BigDecimal
import java.time.Instant

@Node("WalletTransaction")
abstract class WalletTransaction(
    @Id
    var id: String?,
    var value: BigDecimal?,

    @Relationship(type = "HAS")
    var walletTransactionStatus: List<TransactionStatusUpdate>?,

    @Relationship(type = "CHANGES")
    var balance: WalletBalance?,

    var createdAt: Long? = Instant.now().toEpochMilli(),
    var updatedAt: Long?,
) {
    val currentStatus get() = walletTransactionStatus?.lastOrNull()
}
