package com.example.ogmimpersonation;

import org.neo4j.ogm.config.UserSelection;
import org.neo4j.ogm.config.UserSelectionProvider;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * @author Gerrit Meier
 */
@Configuration
public class SomeMoreConfig {

		@Bean
		UserSelectionProvider userSelectionProvider() {
				return () -> UserSelection.impersonate("DEFAULT_USER");
		}


		@Bean
		org.neo4j.ogm.config.Configuration ogmConfiguration(UserSelectionProvider userSelectionProvider) {
				return new org.neo4j.ogm.config.Configuration.Builder()
						.userSelectionProvider(userSelectionProvider)
						.uri("bolt://localhost:7687")
						.credentials("neo4j", "verysecret")
						.build();
		}

		@Bean
		SessionFactory sessionFactory(org.neo4j.ogm.config.Configuration configuration) {
				return new SessionFactory(configuration, "com.example.ogmimpersonation");
		}

}
