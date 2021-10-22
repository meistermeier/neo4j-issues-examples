package com.example.discourse44560;

import org.neo4j.driver.Driver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Discourse44560Application implements CommandLineRunner {

    private final EntityRepository repository;
    private final Driver driver;

    public Discourse44560Application(EntityRepository repository, Driver driver) {
        this.repository = repository;
        this.driver = driver;
    }

    public static void main(String[] args) {
        SpringApplication.run(Discourse44560Application.class, args);
    }

    @Override
    public void run(String... args) {
        try (var session = driver.session()) {
            session.run("MATCH (n) detach delete n");
            session.run("CREATE (:Entity{name:'blubb'})-[:KNOWS]->(:Entity{name:'bla'})");
        }

        RelatedEntityProjection blubb = repository.findByName("blubb");
        System.out.println(blubb.getRelatedEntities().get("KNOWS").get(0).getName());
    }
}
