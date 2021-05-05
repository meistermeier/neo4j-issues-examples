package com.meistermeier.customrepository;

import org.springframework.data.neo4j.core.schema.Node;

import java.util.Objects;

@Node
public class EntityWithAccessionedSequence extends PhysicalEntity {

    @Override
    public String toString() {
        return "EntityWithAccessionedSequence";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityWithAccessionedSequence that = (EntityWithAccessionedSequence) o;
        // don't ask
        return toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        return 11;
    }
}
