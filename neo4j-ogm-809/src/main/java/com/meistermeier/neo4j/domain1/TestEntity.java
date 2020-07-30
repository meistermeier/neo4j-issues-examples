package com.meistermeier.neo4j.domain1;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

/**
 * @author Gerrit Meier
 */
public class TestEntity {

	@GeneratedValue
	@Id
	private Long id;

	private String name;

	public String getName() {
		return name;
	}
}
