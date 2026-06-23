package com.meistermeier.gh3108;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.core.mapping.Neo4jMappingContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Gh3108ApplicationTests {

		@Test
		void metadataCorrect(@Autowired Neo4jMappingContext mappingContext) {
				var nodeDescription = mappingContext.getNodeDescription("NodeA");
				assertThat(nodeDescription).isNotNull();
				assertThat(nodeDescription.getUnderlyingClass()).isEqualTo(NodeA.class);

				assertThat(nodeDescription.getRelationships()
						.stream()
						.filter(relationshipDescription ->
								relationshipDescription.getType().equals("BLUBB"))
						.findFirst()
						.get()
						.getTarget().getUnderlyingClass()).isEqualTo(NodeB.class);
		}

}
