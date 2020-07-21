package com.meistermeier.neo4j;

import static java.util.Collections.*;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

/**
 * @author Gerrit Meier
 */
public class LoadCsvWithOGM {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new SessionFactory(new Configuration.Builder().uri("bolt://localhost:7687")
			.credentials("neo4j", "secret").build(), Artist.class.getPackage().getName());

		Session session = sessionFactory.openSession();
		session.query("MATCH (n) delete n", emptyMap());
		session.query("LOAD CSV FROM 'file:///artists.csv' AS line "
			+ "CREATE (:Artist { name: line[1], year: toInteger(line[2])})", emptyMap());

		System.out.println(session.loadAll(Artist.class));
		sessionFactory.close();
	}
}
