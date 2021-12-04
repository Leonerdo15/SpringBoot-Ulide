package com.iade.ulide.models.repositories;

import com.iade.ulide.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsUsernameAndUsPassword(String username, String password);
}