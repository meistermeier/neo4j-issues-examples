package com.meistermeier.customrepository;

import org.springframework.data.neo4j.core.schema.Node;

import java.util.Objects;

@Node
public class Complex extends PhysicalEntity {

    @Override
    public String toString() {
        return "Complex";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complex that = (Complex) o;
        // don't ask
        return toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        return 12;
    }
}
