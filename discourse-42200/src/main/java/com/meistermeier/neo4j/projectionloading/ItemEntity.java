package com.meistermeier.neo4j.projectionloading;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDate;

@Node("Item")
@AllArgsConstructor
@Data
public class ItemEntity {
    @Id
    private final Integer id;
    private final LocalDate active_date;
    @Property("empty_sapce")
    private final String firstField;
    private final String secondField;
    private final String thirdField;
    @Relationship(type = "HANDLED_BY")
    private final TaskEntity task;
}
