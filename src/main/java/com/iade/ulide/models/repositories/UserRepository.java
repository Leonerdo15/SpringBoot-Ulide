package com.iade.ulide.models.repositories;

import com.iade.ulide.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}