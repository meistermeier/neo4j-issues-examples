package com.example.jsonignore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Gerrit Meier
 */
@RestController
public class OwnerController {

	private final OwnerRepository repository;

	@Autowired
	public OwnerController(OwnerRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public Mono<Owner> getOwnerByName(@RequestParam("name") String name) {
		return repository.findById(name);
	}
}
