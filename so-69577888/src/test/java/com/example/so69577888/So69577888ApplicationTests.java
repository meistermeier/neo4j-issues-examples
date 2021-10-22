package com.example.so69577888;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class So69577888ApplicationTests {

    private final CompanyRepository companyRepository;
    private final OrderRepository orderRepository;
    private final Driver driver;

    @Autowired
    So69577888ApplicationTests(CompanyRepository companyRepository, OrderRepository orderRepository, Driver driver) {
        this.companyRepository = companyRepository;
        this.orderRepository = orderRepository;
        this.driver = driver;
    }

    @BeforeEach
    void setup() {
        try (var session = driver.session()) {
            session.run("MATCH (n) detach delete n").consume();
        }
    }

    @Test
    void shouldCreateCompanyWithRelations() {
        var parent1 = new Company();
        parent1.setName("Parent");

        Company parent = companyRepository.save(parent1);
        var child1 = new Company();
        child1.setName("Child1");
        Company firstChild = companyRepository.save(child1);
        var child2 = new Company();
        child2.setName("Child2");
        Company secondChild = companyRepository.save(child2);
        var order1 = new Order();
        order1.setQuantityOrdered(10);
        order1.setQuantityReceived(10);
        var order2 = new Order();
        order2.setQuantityOrdered(20);
        order2.setQuantityReceived(20);
        Order firstOrder = orderRepository.save(order1);
        Order secondOrder = orderRepository.save(order2);

        var testCompany = new Company();
        testCompany.setName("TestCompany");
        testCompany.setParent(parent);

        testCompany.setChildren(Set.of(firstChild, secondChild));
        firstChild.setParent(testCompany);
        secondChild.setParent(testCompany);
        parent.setChildren(Set.of(testCompany));
        testCompany.setOrders(Set.of(firstOrder, secondOrder));
        companyRepository.save(testCompany);

        var result = companyRepository.findById(testCompany.getId());

        assertThat(result)
                .hasValueSatisfying(company -> {
                    assertThat(company.getId()).isNotNull();
                    assertThat(company.getName()).isEqualTo("TestCompany");
                    assertThat(company.getParent()).isNotNull(); //this assertion fails, returns null
                    assertThat(company.getChildren()).hasSize(2); //this assertion fails, returns 0
                    assertThat(company.getOrders()).hasSize(2);
                });
    }

}
