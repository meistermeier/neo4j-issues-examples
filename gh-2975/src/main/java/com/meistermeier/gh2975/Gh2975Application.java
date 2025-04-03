package com.meistermeier.gh2975;

import org.neo4j.cypherdsl.core.renderer.Configuration;
import org.neo4j.cypherdsl.core.renderer.Dialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Gh2975Application {

	public static void main(String[] args) {
		SpringApplication.run(Gh2975Application.class, args);
	}

	@Bean
	public Configuration configuration() {
		return Configuration.newConfig().withDialect(Dialect.NEO4J_5).build();
	}

}
