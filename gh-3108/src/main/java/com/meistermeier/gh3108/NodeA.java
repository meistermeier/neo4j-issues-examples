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
public class NodeA {

		@Id
		private final String id;

		@Relationship(type = "CONNECTED", direction = Relationship.Direction.INCOMING)
		List<NodeB> bs = new ArrayList<>();

		public NodeA(String id) {
				this.id = id;
		}
}
