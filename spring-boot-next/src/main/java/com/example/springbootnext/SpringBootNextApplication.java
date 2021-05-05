package com.example.springbootnext;

import org.neo4j.driver.Driver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.core.transaction.ReactiveNeo4jTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;

import java.util.List;

@SpringBootApplication
public class SpringBootNextApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootNextApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserRepository repository, Driver driver) {
		return (args) -> {
			driver.session().run("MATCH (n) DETACH DELETE n").consume();
			List<Friend> friends = List.of(new Friend());
			User user = new User(null, friends);
			repository.save(user).block();
		};
	}

	@Bean
	public ReactiveTransactionManager reactiveTransactionManager(Driver driver) {
		return new ReactiveNeo4jTransactionManager(driver);
	}

}
