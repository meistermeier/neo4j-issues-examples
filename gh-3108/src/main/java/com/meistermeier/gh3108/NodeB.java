package com.meistermeier.gh3108;

/**
 *
 * @author Gerrit Meier
 */

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Node
public class NodeB {

		@Id
		private final String id;

		@Relationship(type = "CONNECTED", direction = Relationship.Direction.OUTGOING, cascadeUpdates = false)
		List<NodeA> as = new ArrayList<>();

		public NodeB(String id) {
				this.id = id;
		}
}
