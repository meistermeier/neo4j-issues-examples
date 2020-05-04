package com.example.demo.entity;

import java.util.List;

import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author Gerrit Meier
 */
@NodeEntity
public class Entity extends AbstractEntity {

    protected String name;

    @Index(unique = true)
    protected String uuid;

    protected  String wikiUrl;

    protected String description;

    @Relationship(type="MENTION")
    protected List<RelationshipNeo4j> relationships;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RelationshipNeo4j> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<RelationshipNeo4j> relationships) {
        this.relationships = relationships;
    }

	@Override
	public String toString() {
		return "Entity{" +
				"name='" + name + '\'' +
				", uuid='" + uuid + '\'' +
				", wikiUrl='" + wikiUrl + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
