package com.example.discord64447;

import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Discord64447ApplicationTests {

		@Autowired
		NodeEntityRepository repository;

		@Autowired
		Driver driver;

		@Test
		void contextLoads() {
			try (var session = driver.session()) {
					session.run("MATCH (n) detach delete n").consume();

					session.run("""
       CREATE (r:RootNode{rootNodeName:'name'})-[:Anything]->(n:Node{nodeId:'a'})
       CREATE (n)-[:Serves{rank:1}]->(:Node{name:'c1'})
       CREATE (n)-[:Serves{rank:2}]->(:Node{name:'c2'})
       CREATE (n)-[:Serves{rank:3}]->(:Node{name:'c3'})
       CREATE (n)-[:Serves{rank:4}]->(:Node{name:'c4'})""").consume();
			}

				var result = repository.nodeEntityQuery("name", "a");

				System.out.println(result);


		}

}
