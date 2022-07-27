package com.example.community58196;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ActivityReadRepository extends Neo4jRepository<Activity, Long> {
	Activity findByGuid(String Guid);
}