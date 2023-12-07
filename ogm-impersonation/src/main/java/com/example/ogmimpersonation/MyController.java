package com.example.ogmimpersonation;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

		private final SessionFactory sessionFactory;
		private final MyUserSelectionProvider userSelection;

		MyController(SessionFactory sessionFactory, MyUserSelectionProvider userSelection) {
				this.sessionFactory = sessionFactory;
				this.userSelection = userSelection;
		}

		@GetMapping("/")
		public String accessThings(Authentication authentication) {
				System.out.println(userSelection);
				System.out.println(sessionFactory);
				for (GrantedAuthority authority : authentication.getAuthorities()) {
						System.out.println(authority.getAuthority());
				}
				userSelection.setRole(authentication.getAuthorities().iterator().next().getAuthority());
//					For debugging
//						sessionFactory.openSession()
//								.query("MATCH (n) return n", Map.of());
				return userSelection.getUserSelection().getValue();
		}
}