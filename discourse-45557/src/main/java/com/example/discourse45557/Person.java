package com.example.discourse45557;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class Person {
	@Id
	@GeneratedValue
    private Long id;
	private String name;
	@Relationship(type = "CALLS", direction = Relationship.Direction.OUTGOING)
	private List<Calls> calls;
}
