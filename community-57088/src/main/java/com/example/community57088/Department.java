package com.example.community57088;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Node
@Data
@EqualsAndHashCode(exclude= {"parent", "roleList"}, callSuper = true)
public class Department extends AbstractEntity {

    @Relationship(type = "PARENT", direction = Relationship.Direction.INCOMING)
    private Department parent;

    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    private List<Role> roleList = new ArrayList<>();

}
