package com.example.discord64447;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

/**
 * @author Gerrit Meier
 */
@Getter
@Node("Node")
@ToString
public class NodeEntity {

		@Id String nodeId;

		String name;

		@Relationship(type = "Serves", direction = Relationship.Direction.OUTGOING)
		Set<Serves> outbound;

		public void setNodeId(String nodeId) {
				this.nodeId = nodeId;
		}

		public void setName(String name) {
				this.name = name;
		}

		public void setOutbound(Set<Serves> outbound) {
				this.outbound = outbound;
		}
}
