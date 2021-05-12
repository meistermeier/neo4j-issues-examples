package com.example.so67424479;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.core.mapping.callback.BeforeBindCallback;

@SpringBootApplication
public class So67424479Application {

    public static void main(String[] args) {
        SpringApplication.run(So67424479Application.class, args);
    }

    @Bean
    public BeforeBindCallback<MyEntity> beforeBindMyEntityCallback() {
        return entity -> {
            entity.setName("AnotherName");
            return entity;
        };
    }

}
