package org.neo4j.clusterrouting;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.AccessMode;
import org.neo4j.driver.Driver;
import org.neo4j.driver.SessionConfig;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
class ClusterRoutingApplicationTests {

    @BeforeEach
    void setup(@Autowired Driver driver) {
        try (var session = driver.session();
             var transaction = session.beginTransaction()) {
            transaction.run("MATCH (n) detach delete n");
            transaction.run("CREATE (:User{name:'TestUser'})");
            transaction.commit();
        }
    }

    @Test
    void driverOnlyTest(@Autowired Driver driver) {
        var logger = (Logger) LoggerFactory.getLogger("org.neo4j.driver.internal.async.outbound.OutboundMessageHandler");
        var listAppender = new ListAppender<ILoggingEvent>();
        logger.addAppender(listAppender);
        listAppender.start();
        // method under test
        try (var session = driver.session(SessionConfig.builder().withDefaultAccessMode(AccessMode.READ).build());
             var transaction = session.beginTransaction()) {
            transaction.run("MATCH (n) return n").consume();
            transaction.commit();
        }


        listAppender.stop();
        if (listAppender.list.stream().noneMatch(event -> event.getMessage().contains("C: BEGIN"))) {
            fail("no matching log (C: BEGIN...) to inspect");
        }
        var txBegin = listAppender.list.stream().filter(event -> event.getMessage().contains("C: BEGIN"))
            .findFirst().get();
        assertThat(txBegin.getMessage()).contains("mode=\"r\"");
    }

    @Test
    void readReadOnly(@Autowired SimpleService service) {
        var logger = (Logger) LoggerFactory.getLogger("org.neo4j.driver.internal.async.outbound.OutboundMessageHandler");
        var listAppender = new ListAppender<ILoggingEvent>();
        logger.addAppender(listAppender);
        listAppender.start();
        // method under test
        service.readSomeDataWithReadOnlyTx();


        listAppender.stop();
        if (listAppender.list.stream().noneMatch(event -> event.getMessage().contains("C: BEGIN"))) {
            fail("no matching log (C: BEGIN...) to inspect");
        }
        var txBegin = listAppender.list.stream().filter(event -> event.getMessage().contains("C: BEGIN"))
            .findFirst().get();
        assertThat(txBegin.getMessage()).contains("mode=\"r\"");
    }

    @Test
    void readReadWrite(@Autowired SimpleService service) {
        var logger = (Logger) LoggerFactory.getLogger("org.neo4j.driver.internal.async.outbound.OutboundMessageHandler");
        var listAppender = new ListAppender<ILoggingEvent>();
        logger.addAppender(listAppender);
        listAppender.start();
        // method under test
        service.readSomeDataWithReadWriteTx();


        listAppender.stop();
        if (listAppender.list.stream().noneMatch(event -> event.getMessage().contains("C: BEGIN"))) {
            fail("no matching log (C: BEGIN...) to inspect");
        }
        var txBegin = listAppender.list.stream().filter(event -> event.getMessage().contains("C: BEGIN"))
            .findFirst().get();
        assertThat(txBegin.getMessage()).doesNotContain("mode=\"r\"");
    }

    @Test
    void multipleOperationsShouldStillBeReadOnly(@Autowired SimpleService service) {
        var logger = (Logger) LoggerFactory.getLogger("org.neo4j.driver.internal.async.outbound.OutboundMessageHandler");
        var listAppender = new ListAppender<ILoggingEvent>();
        logger.addAppender(listAppender);
        listAppender.start();
        // method under test
        service.readWithMultipleOperations();

        listAppender.stop();
        if (listAppender.list.stream().noneMatch(event -> event.getMessage().contains("C: BEGIN"))) {
            fail("no matching log (C: BEGIN...) to inspect");
        }
        var txBegin = listAppender.list.stream().filter(event -> event.getMessage().contains("C: BEGIN"))
                .findFirst().get();
        assertThat(txBegin.getMessage()).contains("mode=\"r\"");
    }

    @Test
    void nestedReadOnlyTransactionInWriteTransactionResultsInWriteTransaction(@Autowired SimpleService service) {
        var logger = (Logger) LoggerFactory.getLogger("org.neo4j.driver.internal.async.outbound.OutboundMessageHandler");
        var listAppender = new ListAppender<ILoggingEvent>();
        logger.addAppender(listAppender);
        listAppender.start();
        // method under test
        service.nestedTransactionWithWriteTransaction();

        listAppender.stop();
        if (listAppender.list.stream().noneMatch(event -> event.getMessage().contains("C: BEGIN"))) {
            fail("no matching log (C: BEGIN...) to inspect");
        }
        var txBegin = listAppender.list.stream().filter(event -> event.getMessage().contains("C: BEGIN"))
                .findFirst().get();
        assertThat(txBegin.getMessage()).doesNotContain("mode=\"r\"");
    }

    @Test
    void nestedReadOnlyTransactionInReadOnlyTransactionResultsInReadOnlyTransaction(@Autowired SimpleService service) {
        var logger = (Logger) LoggerFactory.getLogger("org.neo4j.driver.internal.async.outbound.OutboundMessageHandler");
        var listAppender = new ListAppender<ILoggingEvent>();
        logger.addAppender(listAppender);
        listAppender.start();
        // method under test
        service.nestedTransactionWithReadOnlyTransaction();

        listAppender.stop();
        if (listAppender.list.stream().noneMatch(event -> event.getMessage().contains("C: BEGIN"))) {
            fail("no matching log (C: BEGIN...) to inspect");
        }
        var txBegin = listAppender.list.stream().filter(event -> event.getMessage().contains("C: BEGIN"))
                .findFirst().get();
        assertThat(txBegin.getMessage()).contains("mode=\"r\"");
    }
}
