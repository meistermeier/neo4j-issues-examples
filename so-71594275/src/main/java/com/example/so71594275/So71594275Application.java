package com.example.so71594275;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;
import java.util.UUID;

@SpringBootApplication
public class So71594275Application implements CommandLineRunner {

	private final Driver driver;

	@Autowired
	public So71594275Application(Driver driver) {
		this.driver = driver;
	}

	public static void main(String[] args) {
		SpringApplication.run(So71594275Application.class, args);
	}

	@Override
	public void run(String... args) {
		try (Session session = driver.session()) {
			session.run("MATCH (n) detach delete n");
			UUID senderUUID = UUID.randomUUID();
			UUID recipientUUID = UUID.randomUUID();
			session.run("CREATE (u:User{userId:$uuid, name:'sender'})", Map.of("uuid", senderUUID.toString())).consume();
			session.run("CREATE (u:User{userId:$uuid, name:'recipient'})", Map.of("uuid", recipientUUID.toString())).consume();
			System.out.println("Sender UUID: " + senderUUID);
			System.out.println("Recipient UUID: " + recipientUUID);
		}
	}
}
