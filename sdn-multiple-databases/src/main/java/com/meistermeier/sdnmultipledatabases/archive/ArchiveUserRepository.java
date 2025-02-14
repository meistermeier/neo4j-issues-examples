package com.meistermeier.sdnmultipledatabases.archive;

import com.meistermeier.sdnmultipledatabases.user.User;
import com.meistermeier.sdnmultipledatabases.user.UserRepository;

import java.util.List;

/**
 * @author Gerrit Meier
 */
public interface ArchiveUserRepository extends UserRepository {

    List<User> findByName(String name);
}
