package com.example.gh2176;

import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Gh2176ApplicationTests {

    private final Driver driver;
    private final OuterDomainRepository repository;

    @Autowired
    Gh2176ApplicationTests(Driver driver, OuterDomainRepository repository) {
        this.driver = driver;
        this.repository = repository;
    }

    @Test
    void innerDomainLoading() {

        // create one node with the driver
        try (Session session = driver.session()) {
            session.run("MATCH (n) detach delete n").consume();
            session.run("CREATE (:InnerDomain:OuterDomain)").consume();
        }

        // create another one with the repository
        repository.save(new OuterDomain.InnerDomain());

        List<OuterDomain> domains = repository.findAll();
        assertThat(domains).hasSize(2);
        assertThat(domains.get(0)).isOfAnyClassIn(OuterDomain.InnerDomain.class);
    }

}
