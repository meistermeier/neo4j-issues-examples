package com.example.gh2470;

import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Gh2470Application implements CommandLineRunner {

    private final Driver driver;
    private final ServiceRepository repository;

    @Autowired
    public Gh2470Application(Driver driver, ServiceRepository repository) {
        this.driver = driver;
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Gh2470Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try (var session = driver.session()) {
            session.run("MATCH (n) detach delete n");
            session.run("CREATE (s:Service{name:'s1'})");
            session.run("CREATE (s:Service{name:'s2'})");
            session.run("CREATE (s:Service{name:'someService'})");

            session.run("CREATE (s:ServiceOption{name:'s2'})");
            session.run("MATCH (s:Service{name:'someService'}) MATCH (so:ServiceOption{name:'s2'}) CREATE (s)-[:HAS_OPTION]->(so)");
        }

        var s2 = repository.findAllByServiceOrServiceOptionId(List.of("s2"));
        System.out.println(s2);

        repository.save(new ServiceEntity());

    }
}
