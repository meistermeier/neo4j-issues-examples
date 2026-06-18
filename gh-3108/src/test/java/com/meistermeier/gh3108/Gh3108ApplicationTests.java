package com.meistermeier.gh3108;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.core.Neo4jTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Gh3108ApplicationTests {

		@Test
		void savedFromA(@Autowired Neo4jTemplate template) {
				template.deleteAll(NodeA.class);
				template.deleteAll(NodeB.class);
				var a1 = new NodeA("a");
				var b1 = new NodeB("b");
				a1.bs.add(b1);
				template.save(a1);

				var a = template.findById("a", NodeA.class);
				assertThat(a).isNotEmpty()
						.get().extracting(realA -> realA.bs)
						.asInstanceOf(InstanceOfAssertFactories.LIST)
						.isNotEmpty();


				var b = template.findById("b", NodeB.class);
				assertThat(b).isNotEmpty()
						.get().extracting(realB -> realB.as)
						.asInstanceOf(InstanceOfAssertFactories.LIST)
						.isNotEmpty();
		}

		@Test
		void savedFromB(@Autowired Neo4jTemplate template) {
				template.deleteAll(NodeA.class);
				template.deleteAll(NodeB.class);
				var a1 = new NodeA("a");
				var b1 = new NodeB("b");
				b1.as.add(a1);
				template.save(b1);

				var a = template.findById("a", NodeA.class);
				assertThat(a).isNotEmpty()
						.get().extracting(realA -> realA.bs)
						.asInstanceOf(InstanceOfAssertFactories.LIST)
						.isNotEmpty();


				var b = template.findById("b", NodeB.class);
				assertThat(b).isNotEmpty()
						.get().extracting(realB -> realB.as)
						.asInstanceOf(InstanceOfAssertFactories.LIST)
						.isNotEmpty();
		}

}
