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
	void removeRelationship(@Autowired DeviceRepository repository) {
		var foundEntry = repository.findById("dev1");

		assertThat(foundEntry).isPresent();

		var device = foundEntry.get();
		device.setDeviceManager(null);
		repository.save(device);

		var loadedDevice = repository.findById("dev1").get();
		assertThat(loadedDevice.getDeviceManager()).isNull();

	}

}
