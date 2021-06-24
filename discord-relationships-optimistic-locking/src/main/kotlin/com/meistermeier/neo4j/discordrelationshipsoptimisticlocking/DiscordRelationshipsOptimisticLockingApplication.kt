package com.meistermeier.neo4j.discordrelationshipsoptimisticlocking

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Version
import org.springframework.data.neo4j.core.schema.*
import org.springframework.data.neo4j.repository.Neo4jRepository

@SpringBootApplication
class DiscordRelationshipsOptimisticLockingApplication

fun main(args: Array<String>) {
	runApplication<DiscordRelationshipsOptimisticLockingApplication>(*args)
}

@Node
data class Identity(
	@Id @GeneratedValue var id: Long? = null,
	var identifier: String,
	@Relationship(
		type = "FOLLOWS",
		direction = Relationship.Direction.OUTGOING
	) var follows: Set<Relation> = setOf(),
	@Version var version: Long? = null
)

// removed data class here because it will end up in StackOverflow due to a infinite hashCode chain.
@RelationshipProperties
class Relation(
	@Id @GeneratedValue var id: Long? = null,
	@TargetNode var target: Identity,
	var strength: Double
)

interface IdentityRepository : Neo4jRepository<Identity, Long>