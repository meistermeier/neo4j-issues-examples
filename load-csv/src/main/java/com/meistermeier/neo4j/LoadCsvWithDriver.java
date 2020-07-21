package com.meistermeier.neo4j;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

/**
 * @author Gerrit Meier
 */
public class LoadCSV {

	public static void main(String[] args) {
		// load csv with driver
		Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "secret"));
		try (Session session = driver.session()) {
			session.writeTransaction(tx -> {
				// taken example from
				tx.run("LOAD CSV FROM 'https://neo4j.com/docs/cypher-manual/4.1/csv/artists.csv' AS line\n"
					+ "CREATE (:Artist { name: line[1], year: toInteger(line[2])})");
					return null;
			});
		}
	}
}
