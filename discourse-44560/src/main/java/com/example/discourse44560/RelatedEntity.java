package com.example.discourse44560;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
@Data
public class RelatedEntity {

    @Id
    @GeneratedValue()
    private Long id;

    @TargetNode
    private Entity relEntity;

}
