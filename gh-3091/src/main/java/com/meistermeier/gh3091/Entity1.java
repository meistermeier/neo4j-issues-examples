package com.meistermeier.gh3091;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import javax.swing.text.html.parser.Entity;
import java.util.List;

/**
 *
 * @author Gerrit Meier
 */
@Node
class Entity1 {

		@Id
		@GeneratedValue
		private String id;

		private String name;

		@Relationship("RELATED_TO")
		private List<EntityRelationship> entities;

		public String getId() {
				return id;
		}

		public void setId(String id) {
				this.id = id;
		}

		public String getName() {
				return name;
		}

		public void setName(String name) {
				this.name = name;
		}

		public List<EntityRelationship> getEntities() {
				return entities;
		}

		public void setEntities(List<EntityRelationship> entities) {
				this.entities = entities;
		}

		@Override
		public String toString() {
				return "Entity1{" +
							 "entities=" + entities +
							 ", name='" + name + '\'' +
							 '}';
		}

}
