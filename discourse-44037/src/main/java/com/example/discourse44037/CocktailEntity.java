package com.example.discourse44037;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Data
@Node(value = "Cocktail")
public class CocktailEntity {
    @Id
    private String name;

    @Relationship(value = "DESCRIPTION",direction = Relationship.Direction.OUTGOING)
    private Set<StepDescEntity> steps;

}
