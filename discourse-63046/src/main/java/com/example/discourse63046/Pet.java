package com.example.discourse63046;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Pet")
public abstract class Pet {
	@Id
	@GeneratedValue
	public final Long id;

	public final String name;

	protected Pet(Long id, String name) {
		this.id = id;
		this.name = name;
	}
}