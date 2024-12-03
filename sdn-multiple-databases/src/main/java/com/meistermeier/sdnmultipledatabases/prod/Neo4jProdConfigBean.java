package com.meistermeier.sdnmultipledatabases.prod;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.core.mapping.Neo4jMappingContext;
import org.springframework.data.neo4j.core.transaction.Neo4jTransactionManager;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

/**
 * @author Gerrit Meier
 */
@Configuration
@EnableNeo4jRepositories(basePackages = "com.meistermeier.sdnmultipledatabases.prod", neo4jTemplateRef = "neo4jTemplate")
public class Neo4jProdConfigBean {

//    Optional, if it should be in line with the archive config, otherwise the default driver from properties will be used
    @Primary
    @Bean
    public Driver driver() {
        return GraphDatabase.driver("neo4j://localhost:7687", AuthTokens.basic("neo4j", "verysecret"));
    }

    @Primary
    @Bean
    public Neo4jClient neo4jClient1(Driver driver) {
        return Neo4jClient.with(driver).build();
    }

    @Primary
    @Bean
    public Neo4jTemplate neo4jTemplate(Neo4jClient client, Neo4jMappingContext mappingContext, Neo4jTransactionManager transactionManager) {
        return new Neo4jTemplate(client, mappingContext, transactionManager);
    }

    @Primary // always use this tx manager, if nothing else was explicitly defined
    @Bean
    public Neo4jTransactionManager transactionManager(Driver driver) {
        return new Neo4jTransactionManager(driver);
    }
}
