package com.iade.ulide.models.repositories;

import org.springframework.data.repository.CrudRepository;
import com.iade.ulide.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}