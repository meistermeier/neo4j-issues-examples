package com.meistermeier.sdnmultipledatabases.archive;

import org.neo4j.cypherdsl.core.Condition;
import org.neo4j.cypherdsl.core.Cypher;
import org.neo4j.cypherdsl.core.Expression;
import org.neo4j.cypherdsl.core.Node;
import org.neo4j.cypherdsl.core.PatternElement;
import org.neo4j.cypherdsl.core.SortItem;
import org.neo4j.cypherdsl.core.Statement;
import org.neo4j.cypherdsl.core.StatementBuilder;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.core.mapping.CypherGenerator;
import org.springframework.data.neo4j.core.mapping.Neo4jMappingContext;
import org.springframework.data.neo4j.core.mapping.Neo4jPersistentEntity;
import org.springframework.data.neo4j.core.mapping.PropertyFilter;
import org.springframework.data.neo4j.core.transaction.Neo4jTransactionManager;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.repository.query.CustomStatementKreator;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Gerrit Meier
 */
@Configuration
@EnableNeo4jRepositories(basePackages = "com.meistermeier.sdnmultipledatabases.archive", neo4jTemplateRef = "archiveTemplate")
public class Neo4jArchiveConfigBean {

    @Bean("driver2")
    public Driver driver2() {
        return GraphDatabase.driver("neo4j://localhost:7687", AuthTokens.basic("neo4j", "verysecret"));
    }

    @Bean("neo4jClient2")
    public Neo4jClient neo4jClient2(@Qualifier("driver2") Driver driver) {
        return Neo4jClient.with(driver).build();
    }

    @Bean("archiveTemplate")
    public Neo4jTemplate archiveTemplate(@Qualifier("neo4jClient2") Neo4jClient client, Neo4jMappingContext mappingContext, @Qualifier("archiveTransactionManager") Neo4jTransactionManager transactionManager) {
        return new Neo4jTemplate(client, mappingContext, transactionManager);
    }

    @Bean
    @Order(1)
    public CustomStatementKreator customStatementCreator() {
        return new CustomStatementKreator() {

            @Override
            public Statement createStatement(Neo4jPersistentEntity<?> neo4jPersistentEntity, List<PatternElement> matchOn, Condition condition, Predicate<PropertyFilter.RelaxedPropertyPath> includeField, boolean isDistinctReturn, Collection<Expression> returnExpressions, Collection<SortItem> orderBy, Long skip, Number limit, Expression deleteExpression) {
                // just an example for finding all entries
                Node node = Cypher.anyNode().named("n");
                return Cypher.match(node).returning(node).build();
            }

            /*
             e.g. repositoryName: com.meistermeier.sdnmultipledatabases.archive.ArchiveUserRepository
            methodName: User.findByName
             */
            @Override
            public boolean supports(String repositoryName, String methodName) {
                return "com.meistermeier.sdnmultipledatabases.archive.ArchiveUserRepository".equals(repositoryName);
            }
        };
    }

    @Bean("archiveTransactionManager")
    public Neo4jTransactionManager archiveTransactionManager(@Qualifier("driver2") Driver driver) {
        return new Neo4jTransactionManager(driver);
    }
}
