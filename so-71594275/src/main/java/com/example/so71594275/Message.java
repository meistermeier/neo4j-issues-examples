package com.example.so71594275;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.UUID;

@Node
@Data
public class Message {

	@Id
	@GeneratedValue(GeneratedValue.UUIDGenerator.class)
	private UUID messageId;

	private String text;

	@Relationship(type = "SENT",direction = Relationship.Direction.INCOMING)
	private User sent;

	@Relationship("TO")
	private User recipient;
}
