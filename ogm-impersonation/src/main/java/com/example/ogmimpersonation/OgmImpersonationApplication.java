package com.example.ogmimpersonation;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OgmImpersonationApplication implements CommandLineRunner {

		public static void main(String[] args) {
				SpringApplication.run(OgmImpersonationApplication.class, args);
		}

		@Autowired
		private TypicalSingletonBean testBean;

		@Autowired
		private SessionFactory sessionFactory;

		@Override
		public void run(String... args) {
				System.out.println(sessionFactory);
				System.out.println(testBean.doThings());
		}


}
