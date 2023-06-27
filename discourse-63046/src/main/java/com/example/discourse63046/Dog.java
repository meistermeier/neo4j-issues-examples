package com.example.discourse63046;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node("Dog")
public class Dog extends Pet {

	@Relationship(value = "CHEW_TOY", direction = Relationship.Direction.OUTGOING)
	public final List<ChewToy> chewToys;

	public Dog(Long id, String name, List<ChewToy> chewToys) {
		super(id, name);
		this.chewToys = chewToys;
	}
}