package com.meistermeier.gh2898;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * @author Gerrit Meier
 */
@Getter
@Setter
@Node
public class Concept {
	@GeneratedValue
	@Id
	private Long id;


}
