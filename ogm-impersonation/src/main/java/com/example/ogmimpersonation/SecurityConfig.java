package com.example.ogmimpersonation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

		@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
				http
						.authorizeHttpRequests((authorize) -> authorize
								.anyRequest().authenticated()
						)
						.httpBasic(Customizer.withDefaults())
						.formLogin(Customizer.withDefaults());

				return http.build();
		}

		@Bean
		public UserDetailsService userDetailsService() {
				UserDetails userA = User.withDefaultPasswordEncoder()
						.username("user1")
						.password("password")
						.roles("USER")
						.build();
				UserDetails userB = User.withDefaultPasswordEncoder()
						.username("user2")
						.password("password")
						.roles("READER")
						.build();

				return new InMemoryUserDetailsManager(userA,userB);
		}

}