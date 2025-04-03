package org.example.gh2885;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Gerrit Meier
 */
@Node
public class MainNode {

	@GeneratedValue
	@Id
	private String id;

	@Relationship(type = "RELATES_TO", direction = Relationship.Direction.OUTGOING)
	private SortedSet<Neo4jMacroTaskCommand> commands = new TreeSet<>();

	@Relationship(type = "OTHER_RELATIONSHIP", direction = Relationship.Direction.OUTGOING)
	private List<Neo4jMacroTaskCommand> others = new ArrayList<>();

	public String getId() {
		return id;
	}

	public SortedSet<Neo4jMacroTaskCommand> getCommands() {
		return commands;
	}

	public void setOthers(List<Neo4jMacroTaskCommand> others) {
		this.others = others;
	}

	public void setCommands(SortedSet<Neo4jMacroTaskCommand> commands) {
		this.commands = commands;
	}


}
