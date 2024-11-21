package com.meistermeier.gh2952;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * @author Gerrit Meier
 */
@RestController
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/u")
    public Collection<User> userDetail(@QuerydslPredicate(root = User.class) Predicate predicate, Pageable pageable) {
        List<User> content = repository.findAll(predicate, pageable).getContent();
        System.out.println(content);
        return content;
    }
}
