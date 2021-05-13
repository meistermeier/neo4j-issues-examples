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
        try (Session session = driver.session()) {
            session.run("CREATE (:InnerDomain:OuterDomain)").consume();
        }

        List<OuterDomain> domains = repository.findAll();
        assertThat(domains).hasSize(1);
        assertThat(domains.get(0)).isOfAnyClassIn(OuterDomain.InnerDomain.class);
    }

}
