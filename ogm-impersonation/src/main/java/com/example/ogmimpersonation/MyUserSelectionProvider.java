package com.example.ogmimpersonation;

import org.neo4j.ogm.config.UserSelection;
import org.neo4j.ogm.config.UserSelectionProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class MyUserSelectionProvider implements UserSelectionProvider {

		private String role;

		@Override
		public UserSelection getUserSelection() {
				String myRole = switch (role) {
						case "ROLE_USER" -> "userA";
						case "ROLE_READER" -> "userB";
						default -> "neo4j";
				};
				return UserSelection.impersonate(myRole);
		}

		public void setRole(String role) {
				this.role = role;
		}
}