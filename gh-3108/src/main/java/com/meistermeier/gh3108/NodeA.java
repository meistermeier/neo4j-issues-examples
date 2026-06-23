package com.meistermeier.gh3108;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

/**
 *
 * @author Gerrit Meier
 */
@Node
public class NodeA extends NotReallyAbstractNodeA {

	@Relationship(type = "BLUBB")
	private NodeB theB;
}
