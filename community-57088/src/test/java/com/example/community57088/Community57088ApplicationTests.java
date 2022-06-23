package com.example.community57088;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Community57088ApplicationTests {

    @Autowired
    DepartmentRepository repository;

    @Autowired
    Driver driver;

    @BeforeEach
    void setupData() {
        try (var session = driver.session()) {
            session.run("MATCH (n) detach delete n");
            session.run("CREATE (:Department{name:'A', id:1}), (:Department{name:'B', id:2})").consume();
        }
    }

    @Test
    void testCase() {
        Department departmentA = repository.findById(1L).get();
        Department departmentB = repository.findById(2L).get();

        assertThat(departmentA).isNotNull();
        assertThat(departmentB).isNotNull();

        departmentA.setParent(departmentB);

        repository.save(departmentA);

        try (var session = driver.session()) {
            long relCount = session.run("MATCH (a:Department)-[r]->(b:Department) WHERE id(a) <> id(b) RETURN count(r) as relCount")
                    .single().get("relCount").asLong();
            assertThat(relCount).isEqualTo(1);
        }
    }

}
