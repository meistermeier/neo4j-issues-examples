package com.meistermeier.sdnactuator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.core.Neo4jClient;

@SpringBootApplication
public class SdnActuatorApplication implements CommandLineRunner {

	private final Neo4jClient neo4jClient;

	public SdnActuatorApplication(Neo4jClient neo4jClient) {
		this.neo4jClient = neo4jClient;
	}

	public static void main(String[] args) {
		SpringApplication.run(SdnActuatorApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// just create some traffic on application start
		System.out.println(neo4jClient.query("MATCH (n) return n").fetch().all());
	}
}
