package com.meistermeier.neo4j;

import static java.util.Collections.*;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.transaction.Transaction;

/**
 * @author Gerrit Meier
 */
public class GH809 {

	private static final String NEO4J_URI = "neo4j://localhost:7687";
	private static final String NEO4J_USER = "neo4j";
	private static final String NEO4J_PASSWORD = "secret";

	public static void main(String[] args) {
		SessionFactory sessionFactory = new SessionFactory(
			new Configuration.Builder()
				.uri(NEO4J_URI)
				.credentials(NEO4J_USER, NEO4J_PASSWORD)
				.build(),
			"com.meistermeier.neo4j.domain1", "com.meistermeier.neo4j.domain2");

		prepareData(sessionFactory);

		Session session = sessionFactory.openSession();

		com.meistermeier.neo4j.domain1.TestEntity testEntity1 =
			session.queryForObject(com.meistermeier.neo4j.domain1.TestEntity.class, "MATCH (n:Test1) return n",
				emptyMap());

		System.out.println(testEntity1.getName());

		com.meistermeier.neo4j.domain2.TestEntity testEntity2 = session
			.queryForObject(com.meistermeier.neo4j.domain2.TestEntity.class, "MATCH (n:Test2) return n", emptyMap());

		System.out.println(testEntity2.getName());

		sessionFactory.close();
	}

	private static void prepareData(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.query("MATCH (n) detach delete n", emptyMap());
		session.query("CREATE (t:Test1{name:'Test1'})", emptyMap());
		session.query("CREATE (t:Test2{name:'Test2'})", emptyMap());
		transaction.commit();
	}
}
