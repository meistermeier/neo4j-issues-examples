package com.meistermeier.neo4j.relatedIdQuery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.neo4j.core.schema.Node;

@EqualsAndHashCode(callSuper = true)
@Node
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = {@PersistenceConstructor})
public class SidebarBox extends BasicNode {
    private String title;
    private Person person;
}
