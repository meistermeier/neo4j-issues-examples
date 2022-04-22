package com.example.gh2516kt

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.neo4j.driver.Driver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Gh2516KtApplicationTests {

    private var repository: WalletTransactionRepository? = null

    @Autowired
    fun Gh2516ApplicationTests(repository: WalletTransactionRepository, driver: Driver) {
        this.repository = repository
        driver.session().use { session ->
            session.beginTransaction().use { tx ->
                tx.run(
                    "CREATE (w:WalletTransaction:DepositTransaction{id:'bla'}) " +
                            "CREATE (b:WalletBalance{balance: 0}) " +
                            "CREATE (s:TransactionStatusUpdate{updateText:'blubb'}) " +
                            "CREATE (wallet1:Wallet{name:'linkeTasche'}) " +
                            "CREATE (wallet2:Wallet{name:'rechteTasche'}) " +
                            "MERGE (w)-[:CHANGES]->(b) " +
                            "MERGE (w)-[:HAS]->(s) " +
                            "MERGE (w)-[:FROM]->(wallet1) " +
                            "MERGE (w)-[:TO]->(wallet2) "
                )
            }
        }
    }

    @Test
    fun findAll() {
        Assertions.assertThat(repository!!.findAll()).allMatch { walletTransaction ->
            (walletTransaction!!.balance != null && !walletTransaction.walletTransactionStatus!!.isEmpty()
                    && (walletTransaction as DepositTransaction).from != null && (walletTransaction as DepositTransaction).to != null)
        }
    }

}
