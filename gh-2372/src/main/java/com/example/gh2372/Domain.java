package com.example.gh2372;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

public class Domain {

    @Node("Car")
    public static abstract class ACar {
        @Id
        @GeneratedValue
        private Long id;

        private String uuid;
    }

    @Node("Small")
    public static class SmallCar extends ACar {}

    @Node
    public static class OrdinaryCar extends ACar {}

    @Node("BigCar")
    public static class BigCar extends ACar {}
}
