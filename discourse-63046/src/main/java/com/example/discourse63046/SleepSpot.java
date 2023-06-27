package com.example.discourse63046;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * @author Gerrit Meier
 */
@Node
public class SleepSpot {

	@Id
	public final String name;

	public final String location;

	public SleepSpot(String name, String location) {
		this.name = name;
		this.location = location;
	}
}
