package com.example.discourse45557;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.With;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
@Data
@With
@AllArgsConstructor
@RequiredArgsConstructor
public class Calls {
	@Id
	@GeneratedValue
    private Long id;
	@TargetNode
    private final Person target;
	private String day;
}
