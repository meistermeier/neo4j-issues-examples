package com.example.so68938823;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Data
@Node("DATspace")
public class DatSpace {

    @Id
    @GeneratedValue
    private Long neoId;

    @Property("SUPtitle")
    private String title;

    private String SUPid;
}
