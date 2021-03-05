package com.meistermeier.neo4j.relatedIdQuery;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface SidebarRepository extends Neo4jRepository<SidebarBox, Long> {
    List<SidebarBox> findAllByPerson_Id(Long personId);

    List<SidebarBox> findAllByPerson(Person person);
}
