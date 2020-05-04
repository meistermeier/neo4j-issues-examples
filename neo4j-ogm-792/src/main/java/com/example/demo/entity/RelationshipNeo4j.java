package com.example.demo.entity;

import java.time.LocalDate;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * @author Gerrit Meier
 */
@RelationshipEntity(type = "MENTION")
public class RelationshipNeo4j extends AbstractEntity {

    @Property
    protected String type;

    @Property
    protected LocalDate date;

    @StartNode
    protected Entity start;

    @EndNode
    protected Entity end;

	@Override
	public String toString() {
		return "RelationshipNeo4j{" +
				"type='" + type + '\'' +
				", date=" + date +
				", start=" + start +
				", end=" + end +
				'}';
	}

	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Entity getStart() {
        return start;
    }

    public void setStart(Entity start) {
        this.start = start;
    }

    public Entity getEnd() {
        return end;
    }

    public void setEnd(Entity end) {
        this.end = end;
    }
}
