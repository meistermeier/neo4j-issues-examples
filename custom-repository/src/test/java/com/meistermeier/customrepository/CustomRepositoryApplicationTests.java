package com.meistermeier.customrepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.neo4j.driver.types.MapAccessor;
import org.neo4j.driver.types.TypeSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.core.mapping.Neo4jMappingContext;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomRepositoryApplicationTests {

    @BeforeEach
    void setupData(@Autowired Driver driver) {
        try (var session = driver.session()) {
            session.run("MATCH (n) detach delete n");
            session.run("CREATE (n:DatabaseObject:PhysicalEntity{dbId:123})-[:output]->(:Complex:DatabaseObject:PhysicalEntity) "
                   + "CREATE (n)-[:output]->(:EntityWithAccessionedSequence:DatabaseObject:PhysicalEntity) return id(n) as id").single().get("id").asLong();
        }
    }

    @Test
    void mapBasedOnClient(@Autowired Neo4jClient neo4jClient, @Autowired Neo4jMappingContext neo4jMappingContext) {
        BiFunction<TypeSystem, MapAccessor, PhysicalEntity> mappingFunction = neo4jMappingContext.getRequiredMappingFunctionFor(PhysicalEntity.class);
        Map<String, Object> map = Map.of("dbId", 123);
        Collection<QueryResultWrapper> wrapper = neo4jClient.query("MATCH (a:DatabaseObject{dbId:$dbId})-[r:output]->(m:PhysicalEntity) RETURN m, 3 as n")
                .bindAll(map).fetchAs(QueryResultWrapper.class)
                .mappedBy((typeSystem, record) -> {
                    DatabaseObject n = mappingFunction.apply(typeSystem, record.get("m"));
                    int number = record.get("n").asInt();
                    return new QueryResultWrapper(n, number);
                }).all();

        assertThat(wrapper).containsExactlyInAnyOrder(
                new QueryResultWrapper(new Complex(), 3),
                new QueryResultWrapper(new EntityWithAccessionedSequence(), 3));
    }

    static class QueryResultWrapper {
        private final DatabaseObject databaseObject;
        private final Integer number;

        QueryResultWrapper(DatabaseObject databaseObject, Integer number) {
            this.databaseObject = databaseObject;
            this.number = number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            QueryResultWrapper that = (QueryResultWrapper) o;
            return databaseObject.equals(that.databaseObject) && number.equals(that.number);
        }

        @Override
        public int hashCode() {
            return Objects.hash(databaseObject, number);
        }
    }

}
