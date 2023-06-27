package com.example.discourse63046;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node("Family")
public class Family {
	@Id
	@GeneratedValue
	public final Long id;

	public final String name;

	@Relationship(value = "PET", direction = Relationship.Direction.OUTGOING)
	public final List<Pet> pets;

	public Family(Long id, String name, List<Pet> pets) {
		this.id = id;
		this.name = name;
		this.pets = pets;
	}
}