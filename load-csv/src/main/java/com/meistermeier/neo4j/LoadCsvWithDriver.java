package com.meistermeier.neo4j;

import java.util.List;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Session;
import org.neo4j.driver.Value;

/**
 * @author Gerrit Meier
 */
public class LoadCsvWithDriver {

	public static void main(String[] args) {

		try (Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "secret"));
			Session session = driver.session()) {
			session.writeTransaction(tx -> {
				tx.run("MATCH (n) delete n");
				tx.run("LOAD CSV FROM 'file:///artists.csv' AS line "
					+ "CREATE (:Artist { name: line[1], year: toInteger(line[2])})");
				return null;
			});

			List<Record> result = session.readTransaction(tx -> tx.run("MATCH (a:Artist) return a").list());

			result.forEach(record -> {
				Value artist = record.get("a");
				System.out.println(artist.get("name"));
			});
		}
	}
}
