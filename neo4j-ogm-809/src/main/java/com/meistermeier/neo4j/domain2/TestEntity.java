package com.meistermeier.neo4j.domain2;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * @author Gerrit Meier
 */
@NodeEntity("Test2")
public class TestEntity {

	@GeneratedValue
	@Id
	private Long id;

	private String name;

	public String getName() {
		return name;
	}
}
