package com.meistermeier.transactiontimeout;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
@Import(Neo4jTestConnection.class)
class TransactionTimeoutTests {

    private final String EXPECTED_TIMEOUT_MESSAGE = "The transaction has been terminated. Retry your operation in a new transaction";

    @DynamicPropertySource
    static void setApplicationProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.transaction.default-timeout", () -> Duration.ofSeconds(2));
    }

    @Test
    void springTransactionalTimeoutAnnotation(@Autowired UserService userService) {
        assertThatExceptionOfType(InvalidDataAccessResourceUsageException.class)
            .isThrownBy(userService::createLotsOfUsersWithAnnotationTimeout)
            .withMessageContaining(EXPECTED_TIMEOUT_MESSAGE);
    }

    @Test
    void springTransactionalTimeoutProperty(@Autowired UserService userService) {
        assertThatExceptionOfType(InvalidDataAccessResourceUsageException.class)
            .isThrownBy(userService::createLotsOfUsersWithoutAnnotationTimeout)
            .withMessageContaining(EXPECTED_TIMEOUT_MESSAGE);
    }

}
