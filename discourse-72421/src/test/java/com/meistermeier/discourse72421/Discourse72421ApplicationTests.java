package com.meistermeier.discourse72421;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ImportTestcontainers(Neo4jContainerConfiguration.class)
class Discourse72421ApplicationTests {

	@BeforeEach
	void setup(@Autowired Driver driver) {
		driver.executableQuery("CREATE (:Device{name:'device1', version: 0, id: 'dev1'})-[:MANAGED_BY]->(:DeviceManager{name:'manager1', version:0, id:'mgr1'})")
			.execute();
	}

	@Test
	void removeRelationshipSuccessfully(@Autowired DeviceRepository repository) {
		var foundEntry = repository.findById("dev1");

		assertThat(foundEntry).isPresent();

		var device = foundEntry.get();
		device.setDeviceManagerRelationship(null);
		repository.save(device);

		var loadedDevice = repository.findById("dev1").get();
		assertThat(loadedDevice.getDeviceManagerRelationship()).isNull();

	}
	@Test
	void removeRelationshipNotSuccessfully(@Autowired DeviceRepository repository, @Autowired Driver driver) {

		try (var session = driver.session()) {
			session.run("MATCH (n)-[r]->(m) return n, r, m")
				.forEachRemaining(record -> System.out.printf("%s %s %s", record.get("n").asNode(), record.get("r").asRelationship(), record.get("m").asNode()));
		}

		var foundEntry = repository.findById("dev1");

		assertThat(foundEntry).isPresent();

		var device = foundEntry.get();
		device.getDeviceManagerRelationship().deviceManager = new DeviceManager("mgr2", "manager2");
		repository.save(device);

		try (var session = driver.session()) {
			session.run("MATCH (n)-[r]->(m) return n, r, m")
				.forEachRemaining(record -> System.out.printf("%s %s %s", record.get("n").asNode(), record.get("r").asRelationship(), record.get("m").asNode()));
		}

		var loadedDevice = repository.findById("dev1").get();
		assertThat(loadedDevice.getDeviceManagerRelationship()).isNull();

	}

}
