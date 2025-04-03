package org.example.gh2885;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.util.List;
import java.util.TreeSet;

@SpringBootTest
@EnableNeo4jRepositories(considerNestedRepositories = true)
class Gh2885ApplicationTests {

//	@BeforeEach
//	void cleanUp(@Autowired Driver driver) {
//		driver.executableQuery("MATCH (n) detach delete n").execute();
//	}

@Test
void findPersonWithMovies(@Autowired PersonRepository repository){
	var person = repository.findByName("Laurence Fishburne");
	System.out.println(person);
}
	@Test
	void saveNodeWithRelationships(@Autowired Neo4jTemplate template) {
		MainNode mainNode = new MainNode();
		TreeSet<Neo4jMacroTaskCommand> set = new TreeSet<>();
		Neo4jMacroTaskCommand command1 = new Neo4jMacroTaskCommand(1, new Neo4jImplementation("my implementation"));
		Neo4jMacroTaskCommand command2 = new Neo4jMacroTaskCommand(1, new Neo4jImplementation("other"));
		set.add(command1);
		mainNode.setCommands(set);
		mainNode.setOthers(List.of(command2));
		template.save(mainNode);
	}

}
