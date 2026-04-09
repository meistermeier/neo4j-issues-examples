package com.meistermeier.gh3091;

import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 *
 * @author Gerrit Meier
 */
interface Entity1Repository extends Neo4jRepository<Entity1, String> {
}
