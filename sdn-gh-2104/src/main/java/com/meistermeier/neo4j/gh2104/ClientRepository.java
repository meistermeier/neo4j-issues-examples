package com.meistermeier.neo4j.gh2104;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * @author Gerrit Meier
 */
public interface ClientRepository extends Neo4jRepository<ClientNode, Long> {

	Optional<ClientNode> findByCode(String code);
}
