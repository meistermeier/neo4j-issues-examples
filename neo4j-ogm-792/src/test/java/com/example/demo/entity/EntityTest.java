package com.example.demo.entity;

import static java.util.Collections.*;

import org.junit.jupiter.api.Test;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Gerrit Meier
 */
@SpringBootTest
public class EntityTest {

	@Autowired
	private RelationshipNeo4jRepository repository;

	@Autowired
	private SessionFactory sessionFactory;

    @Test
	void asdf() {
		Session session = sessionFactory.openSession();
		session.query("MATCH (n) detach delete n",
				emptyMap()).queryStatistics();
		session.query("CREATE (e:Entity {uuid:'1'}), (e2:Entity{uuid:'2'})",
				emptyMap()).queryStatistics();

		System.out.println(repository.createRelationshipBetweenExistingEntities("1", "2", "3", "asdf", "2011-12-03"));
		sessionFactory.close();
	}

}
