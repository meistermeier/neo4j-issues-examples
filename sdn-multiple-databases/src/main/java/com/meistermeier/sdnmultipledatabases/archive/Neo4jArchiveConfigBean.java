package com.meistermeier.sdnmultipledatabases.archive;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.core.mapping.Neo4jMappingContext;
import org.springframework.data.neo4j.core.transaction.Neo4jTransactionManager;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

/**
 * @author Gerrit Meier
 */
@Configuration
@EnableNeo4jRepositories(basePackages = "com.meistermeier.sdnmultipledatabases.archive", neo4jTemplateRef = "archiveTemplate")
public class Neo4jArchiveConfigBean {

    @Bean("driver2")
    public Driver driver2() {
        return GraphDatabase.driver("neo4j://localhost:17687", AuthTokens.basic("neo4j", "verysecret"));
    }

    @Bean("neo4jClient2")
    public Neo4jClient neo4jClient2(@Qualifier("driver2") Driver driver) {
        return Neo4jClient.with(driver).build();
    }

    @Bean("archiveTemplate")
    public Neo4jTemplate archiveTemplate(@Qualifier("neo4jClient2") Neo4jClient client, Neo4jMappingContext mappingContext, @Qualifier("archiveTransactionManager") Neo4jTransactionManager transactionManager) {
        return new Neo4jTemplate(client, mappingContext, transactionManager);
    }

    @Bean("archiveTransactionManager")
    public Neo4jTransactionManager archiveTransactionManager(@Qualifier("driver2") Driver driver) {
        return new Neo4jTransactionManager(driver);
    }
}
