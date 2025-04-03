package com.meistermeier.sdnactuator;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gerrit Meier
 */
@RestController
public class SomeController {

		@GetMapping("/")
		public String classloader() throws Exception {
				ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
				System.out.println(contextClassLoader);
				return contextClassLoader.loadClass("org.junit.jupiter.api.Test").getClassLoader().toString();
		}
}
