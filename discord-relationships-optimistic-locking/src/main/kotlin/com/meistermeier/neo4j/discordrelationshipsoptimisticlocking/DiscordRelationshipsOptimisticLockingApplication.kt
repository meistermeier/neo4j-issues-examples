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

/**
 * CAUTION: This data class now has a mixture of constructor-only and mutable properties.
 * Spring Data cannot really work with both within the same class.
 * Since the relationships need to be mutable, the whole class will have to be mutable in the end (var properties)
 */
@Node
data class Identity(
	@Id @GeneratedValue val id: Long? = null,
	var identifier: String,
	@Relationship(
		type = "FOLLOWS",
		direction = Relationship.Direction.OUTGOING
	) var follows: Set<Relation> = setOf(),
	@Version val version: Long? = null
) {
	fun withId(newId: Long): Identity {
		return Identity(newId, identifier, follows, version)
	}
	fun withIdentifier(newIdentifier: String): Identity {
		return Identity(id, newIdentifier, follows, version)
	}
	fun withFollows(newFollows: Set<Relation>): Identity {
		return this.copy(follows = newFollows)
	}
	fun withVersionId(newVersion: Long): Identity {
		return Identity(id, identifier, follows, newVersion)
	}
}

@RelationshipProperties
data class Relation(
	@Id @GeneratedValue val id: Long? = null,
	@TargetNode val target: Identity,
	val strength: Double
) {
	// omitted wither methods, hashcode, equals...
}

interface IdentityRepository : Neo4jRepository<Identity, Long>