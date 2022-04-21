package com.example.gh2516;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.math.BigDecimal;
import java.util.List;

@Node("WalletTransaction")
public abstract class WalletTransaction {

	@Id
	public String id;
	public BigDecimal value;

	@Relationship(type = "HAS")
	public List<TransactionStatusUpdate> walletTransactionStatus;

	@Relationship(type = "CHANGES")
	public WalletBalance balance;

	public Long createdAt;
	public Long updatedAt;
}
