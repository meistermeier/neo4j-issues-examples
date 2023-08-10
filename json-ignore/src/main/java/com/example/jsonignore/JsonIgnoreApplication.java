package com.example.jsonignore;

import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.core.transaction.ReactiveNeo4jTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;

@SpringBootApplication
public class JsonIgnoreApplication implements CommandLineRunner {

	private final Driver driver;

	@Autowired
	public JsonIgnoreApplication(Driver driver) {
		this.driver = driver;
	}

	@Bean
	public ReactiveTransactionManager reactiveTransactionManager(Driver driver) {
		return new ReactiveNeo4jTransactionManager(driver);
	}

	public static void main(String[] args) {
		SpringApplication.run(JsonIgnoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try (var session = driver.session()) {
			session.run("MATCH (n) detach delete n").consume();
			session.run("""
    CREATE (o:Owner{name:'Gerrit'})
    CREATE (p1:Pet{name:'Luna'})
    MERGE (o)-[:OWNS]->(p1)
				""").consume();
		}
	}
}
