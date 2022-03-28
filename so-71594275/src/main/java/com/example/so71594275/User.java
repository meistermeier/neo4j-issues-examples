package com.example.so71594275;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.UUID;

@Node
@Data
public class User {

	@Id
	@GeneratedValue(GeneratedValue.UUIDGenerator.class)
	private UUID userId;

	private String name;
}
