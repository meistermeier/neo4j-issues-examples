package com.meistermeier.gh3091;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 *
 * @author Gerrit Meier
 */
@Node
class Entity2 {

		@Id
		@GeneratedValue
		private String id;

		private String name;

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

		@Override
		public String toString() {
				return "Entity2{" +
							 "id='" + id + '\'' +
							 ", name='" + name + '\'' +
							 '}';
		}
}
