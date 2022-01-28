package com.example.so69577888;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class So69577888Application implements CommandLineRunner {

    @Autowired
    private Driver driver;

    @Autowired
    private MovieRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(So69577888Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.findAll().forEach(m -> System.out.println(m.title));
    }
}
