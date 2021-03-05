package com.meistermeier.neo4j.relatedIdQuery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.DateLong;

import java.util.Date;

@Node
@Data
@AllArgsConstructor(onConstructor_ = {@PersistenceConstructor})
@NoArgsConstructor
public abstract class BasicNode {
    @Id
    @GeneratedValue
    private Long id;

    @DateLong
    @LastModifiedDate
    private Date changedAt = new Date();

    @DateLong
    @CreatedDate
    private Date enteredAt = new Date();
}
