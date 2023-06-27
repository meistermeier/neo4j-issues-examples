package com.example.discourse63046;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * @author Gerrit Meier
 */
@Node
public class ChewToy {
	@Id
	public final String name;

	public final Integer usedState;

	public ChewToy(String name, Integer usedState) {
		this.name = name;
		this.usedState = usedState;
	}
}
