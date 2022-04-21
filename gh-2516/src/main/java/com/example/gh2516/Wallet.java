package com.example.gh2516;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Wallet {

	@Id
	@GeneratedValue
	public Long id;

	private String name;
}
