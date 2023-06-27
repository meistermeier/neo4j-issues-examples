package com.example.discourse63046;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node("Cat")
public class Cat extends Pet {

	@Relationship(value = "SLEEP_SPOT", direction = Relationship.Direction.OUTGOING)
	public final List<SleepSpot> sleepSpots;

	public Cat(Long id, String name, List<SleepSpot> sleepSpots) {
		super(id, name);
		this.sleepSpots = sleepSpots;
	}
}