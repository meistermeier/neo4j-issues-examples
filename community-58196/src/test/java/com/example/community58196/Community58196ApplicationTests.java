package com.example.community58196;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Community58196ApplicationTests {

	private final Driver driver;
	private final ActivityReadRepository activityRepository;

	@Autowired
	Community58196ApplicationTests(Driver driver, ActivityReadRepository activityRepository) {
		this.driver = driver;
		this.activityRepository = activityRepository;
	}

	@Test
	void verifyInteraction() {
		String parentGuid = "123";
		String childToRemoveGuid = "456";
		String childToKeepGuid = "789";

		try (var session = driver.session()) {
			session.run("MATCH (n) detach delete n");
			session
					.run("CREATE (:Activity{guid:$childToKeepGuid})<-[:PARENT_OF]-(:Activity{guid:$parentGuid})-[:PARENT_OF]->(:Activity{guid:$childToRemoveGuid})",
							Map.of("childToKeepGuid", childToKeepGuid, "parentGuid", parentGuid, "childToRemoveGuid", childToRemoveGuid))
					.consume();
		}

		Activity parent = activityRepository.findByGuid(parentGuid);
		Activity child = activityRepository.findByGuid(childToRemoveGuid);

		parent.removeChild(child);
		activityRepository.save(parent);

		Activity parentAfterModification = activityRepository.findByGuid(parentGuid);
		assertThat(parentAfterModification.getChildren()).extracting("guid").containsExactly(childToKeepGuid);
	}

}
