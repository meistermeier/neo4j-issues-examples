package com.example.gh2516;

import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Gh2516ApplicationTests {

	private final WalletTransactionRepository repository;

	@Autowired
	Gh2516ApplicationTests(WalletTransactionRepository repository, Driver driver) {
		this.repository = repository;
		try (Session session = driver.session(); Transaction tx = session.beginTransaction()) {
			tx.run( "CREATE (w:WalletTransaction:DepositTransaction{id:'bla'}) " +
					"CREATE (b:WalletBalance{balance: 0}) " +
					"CREATE (s:TransactionStatusUpdate{updateText:'blubb'}) " +
					"CREATE (wallet1:Wallet{name:'linkeTasche'}) " +
					"CREATE (wallet2:Wallet{name:'rechteTasche'}) " +
					"MERGE (w)-[:CHANGES]->(b) " +
					"MERGE (w)-[:HAS]->(s) " +
					"MERGE (w)-[:FROM]->(wallet1) " +
					"MERGE (w)-[:TO]->(wallet2) "
			);
		}
	}

	@Test
	void findAll() {
		assertThat(repository.findAll()).allMatch(walletTransaction ->
				walletTransaction.balance != null
				&& !walletTransaction.walletTransactionStatus.isEmpty()
				&& ((DepositTransaction) walletTransaction).from != null
				&& ((DepositTransaction) walletTransaction).to != null
		);
	}

}
