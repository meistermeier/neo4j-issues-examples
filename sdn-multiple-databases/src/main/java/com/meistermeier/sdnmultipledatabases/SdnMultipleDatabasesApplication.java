package com.meistermeier.sdnmultipledatabases;

import org.neo4j.cypherdsl.core.renderer.Configuration;
import org.neo4j.cypherdsl.core.renderer.Dialect;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SdnMultipleDatabasesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SdnMultipleDatabasesApplication.class, args);
    }

    @Bean
    public CommandLineRunner testDataCreator(Driver driver, @Qualifier("driver2") Driver archiveDriver) {
        return args -> {
            driver.executableQuery("CREATE (u:User{name:'someone'})").execute();
            archiveDriver.executableQuery("CREATE (u:User{name:'archived user'})").execute();
        };
    }

    @Bean
    public Configuration cypherDslConfiguration() {
        return Configuration.newConfig().withDialect(Dialect.NEO4J_5).build();
    }

}
