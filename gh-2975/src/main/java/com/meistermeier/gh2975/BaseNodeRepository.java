package com.meistermeier.gh2975;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

/**
 * @author Gerrit Meier
 */
public interface BaseNodeRepository extends Neo4jRepository<BaseNode, String> {
}
