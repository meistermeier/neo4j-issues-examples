package com.meistermeier.sdnmultipledatabases.user;

import com.meistermeier.sdnmultipledatabases.archive.ArchiveUserRepository;
import com.meistermeier.sdnmultipledatabases.prod.ProdUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Gerrit Meier
 */
@RestController
public class UserController {

    private final ArchiveUserRepository archiveRepository;
    private final ProdUserRepository prodRepository;

    public UserController(ArchiveUserRepository archiveRepository, ProdUserRepository prodRepository) {
        this.archiveRepository = archiveRepository;
        this.prodRepository = prodRepository;
    }

    @GetMapping("/u")
    public List<User> findAllProdUsers() {
        return prodRepository.findAll();
    }
    @GetMapping("/a")
    public List<User> findAllArchivedUsers() {
        return archiveRepository.findAll();
    }
}
