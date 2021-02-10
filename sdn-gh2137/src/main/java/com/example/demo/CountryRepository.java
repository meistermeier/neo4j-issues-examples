package com.example.demo;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface CountryRepository extends Neo4jRepository<Country, String> {

    Optional<Country> findByCode(String code);
}
