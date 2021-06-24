package com.meistermeier.neo4j.discordrelationshipsoptimisticlocking

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.neo4j.driver.Driver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DiscordRelationshipsOptimisticLockingApplicationTests {

    @BeforeEach
    fun cleanup(@Autowired driver: Driver) {
        driver.session().use { it.run("MATCH (n) DETACH DELETE n") }
    }

    @Test
    fun doesThings(@Autowired identityRepository: IdentityRepository) {
        val user1 = Identity(identifier = "1")
        val user2 = Identity(identifier = "2")

        val relation1To2 = Relation(target = user2, strength = 10.0)
        val relation2To1 = Relation(target = user1, strength = 200.0)

        user1.follows = setOf(relation1To2)
        user2.follows = setOf(relation2To1)
//        Won't work because the user1 / user1WithFollow and user2 / user2WithFollows are different objects
//        In this case two completely separated sub-graphs will get created (1->2) and (2->1)
//        val user1WithFollows = user1.withFollows(setOf(relation1To2))
//        val user2WithFollows = user2.withFollows(setOf(relation2To1))

        identityRepository.saveAll(listOf(user1, user2))

    }

}
