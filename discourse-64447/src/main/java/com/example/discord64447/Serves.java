package com.example.discord64447;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

/**
 * @author Gerrit Meier
 */
@RelationshipProperties
@Data
public class Serves {

		@RelationshipId private Long id;

		private Integer rank;

		@TargetNode
		NodeEntity targetEntity;
}
