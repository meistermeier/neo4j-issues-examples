package com.meistermeier.discourse71553;

import org.neo4j.cypherdsl.core.renderer.Configuration;
import org.neo4j.cypherdsl.core.renderer.Dialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.neo4j.config.EnableNeo4jAuditing;

import java.util.Optional;

@SpringBootApplication
@EnableNeo4jAuditing
public class Discourse71553Application {

	public static void main(String[] args) {
		SpringApplication.run(Discourse71553Application.class, args);
	}

	@Bean
	public AuditorAware<String> userAuditorAware() {
		return () -> Optional.of("me");
	}

	@Bean
	public Configuration configuration() {
		return Configuration.newConfig().withDialect(Dialect.NEO4J_5).build();
	}

}
