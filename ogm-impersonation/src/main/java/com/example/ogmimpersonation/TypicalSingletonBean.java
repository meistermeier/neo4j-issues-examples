package com.example.ogmimpersonation;

import org.neo4j.ogm.config.UserSelectionProvider;
import org.springframework.stereotype.Component;

@Component
public class TypicalSingletonBean {
		private final UserSelectionProvider userSelectionProvider;

		TypicalSingletonBean(UserSelectionProvider userSelectionProvider) {
				this.userSelectionProvider = userSelectionProvider;
		}

		public String doThings() {
				return this.userSelectionProvider.getUserSelection().getValue();
		}
}