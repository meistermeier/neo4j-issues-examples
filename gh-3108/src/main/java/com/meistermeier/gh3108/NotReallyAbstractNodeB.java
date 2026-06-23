package com.meistermeier.gh3108;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

/**
 *
 * @author Gerrit Meier
 */
@Node
public class NotReallyAbstractNodeB {
	@Id
	@GeneratedValue
	private String id;

	@Relationship(type = "BLUBB", direction = Relationship.Direction.INCOMING)
	private NotReallyAbstractNodeA theA;
}
