package com.meistermeier.sdnmultipledatabases;

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
            System.out.println(driver == archiveDriver);
//            driver.executableQuery("CREATE (u:User{name:'prod user'})").execute();
//            archiveDriver.executableQuery("CREATE (u:User{name:'archived user'})").execute();
        };
    }

}
