package com.example.gh2516;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node("DepositTransaction")
public class DepositTransaction extends WalletTransaction {

	@Relationship("FROM")
	public Wallet from;

	@Relationship("TO")
	public Wallet to;
}
