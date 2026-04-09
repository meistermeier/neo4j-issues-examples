package com.meistermeier.gh3091;

import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

/**
 *
 * @author Gerrit Meier
 */
@RelationshipProperties
class EntityRelationship {

		@RelationshipId
		private String id;

		private String someProperty;

		@TargetNode
		private Entity2 entity2;

		public String getId() {
				return id;
		}

		public void setId(String id) {
				this.id = id;
		}

		public Entity2 getEntity2() {
				return entity2;
		}

		public void setEntity2(Entity2 entity2) {
				this.entity2 = entity2;
		}

		public String getSomeProperty() {
				return someProperty;
		}

		public void setSomeProperty(String someProperty) {
				this.someProperty = someProperty;
		}

		@Override
		public String toString() {
				return "EntityRelationship{" +
							 "entity2=" + entity2 +
							 ", id='" + id + '\'' +
							 '}';
		}
}
