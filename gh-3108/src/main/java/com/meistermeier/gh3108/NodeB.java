package com.meistermeier.gh3108;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

/**
 *
 * @author Gerrit Meier
 */
@Node
public class NodeB extends NotReallyAbstractNodeB {

	@Relationship(type = "BLUBB", direction = Relationship.Direction.INCOMING)
	private NodeB theA;
}
