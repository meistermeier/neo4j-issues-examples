package com.meistermeier.gh2952;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class Gh2952Application {

	public static void main(String[] args) {
		SpringApplication.run(Gh2952Application.class, args);
	}

}
