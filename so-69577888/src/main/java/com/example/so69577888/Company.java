package com.example.so69577888;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.Set;

@Node
public class Company {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    private String name;

    @Relationship(type = "COLLECTS_FROM", direction = Relationship.Direction.INCOMING)
    private Company parent;

    @Relationship(type = "COLLECTS_FROM")
    private Set<Company> children;

    @Relationship(type = "DELIVERS")
    private Set<Order> orders;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getParent() {
        return parent;
    }

    public void setParent(Company parent) {
        this.parent = parent;
    }

    public Set<Company> getChildren() {
        return children;
    }

    public void setChildren(Set<Company> children) {
        this.children = children;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
