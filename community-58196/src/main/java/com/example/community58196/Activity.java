package com.example.community58196;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Objects;
import java.util.Set;

@Node
public class Activity {
    @Id
    @GeneratedValue
    protected Long graphId;
    private String guid;
    @Relationship(type = "PARENT_OF", direction = Relationship.Direction.OUTGOING)
    private Set<Activity> children;
    
    public void addChild(Activity child) {
      this.children.add(child);
    }
    public void removeChild(Activity child) {
     this.children.remove(child);
    }

    public Set<Activity> getChildren() {
        return children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return graphId.equals(activity.graphId) && guid.equals(activity.guid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(graphId, guid);
    }
}