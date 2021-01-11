package com.meistermeier.neo4j.gh2104;

import org.springframework.data.neo4j.core.schema.Node;

/**
 * @author Gerrit Meier
 */
@Node
public class ClientNode extends BaseEntity {
	private String note;

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
