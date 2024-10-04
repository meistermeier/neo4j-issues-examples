package com.meistermeier.transactiontimeout;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.utility.DockerImageName;

/**
 * @author Gerrit Meier
 */
@TestConfiguration(proxyBeanMethods = false)
public class Neo4jTestConnection {

    @ServiceConnection(name = "neo4j")
    @Bean
    public Neo4jContainer<?> neo4jContainer() {
        return new Neo4jContainer<>(DockerImageName.parse("neo4j:5.24.1")).withRandomPassword();
    }

}
