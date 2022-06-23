package com.example.community57088;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface DepartmentRepository extends Neo4jRepository<Department, Long> {
}
