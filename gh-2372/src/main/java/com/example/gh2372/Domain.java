package com.example.gh2372;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

public class Domain {

    @Node("Vehicle")
    public static abstract class Vehicle {

        @Id
        private String uuid;

        private Boolean archived;

        String generalPropertyOfTheCar;

        private List<String> labels;

        @Relationship(type = "keepsTo")
        private Route route;
    }

    @Node
    public static class Route {
        @Id
        @GeneratedValue
        private Long id;
    }

    @Node({"SmallCar","Car"})
    public static class SmallCar extends Vehicle {
        String propertyOfSmallCar;
    }

    @Node("Car")
    public static class OrdinaryCar extends Vehicle {}

    @Node({"BigCar","Car"})
    public static class BigCar extends Vehicle {
        String propertyOfBigCar;
    }
}
