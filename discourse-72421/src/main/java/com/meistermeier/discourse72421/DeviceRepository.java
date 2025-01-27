package com.meistermeier.discourse72421;

import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * @author Gerrit Meier
 */
public interface DeviceRepository extends Neo4jRepository<Device, String> {
}
