package com.iade.ulide.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.iade.ulide.models.TypeUser;
import com.iade.ulide.models.exceptions.NotFoundException;
import com.iade.ulide.models.exceptions.Response;
import com.iade.ulide.models.repositories.TypeUserRepository;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/typeUsers")
public class TypeUserController {
    private final Logger logger = LoggerFactory.getLogger(TypeUserController.class);

    @Autowired
    private TypeUserRepository typeUserRepository;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<TypeUser> getTypeUsers() {
        logger.info("Sending all Type Users !!!");
        return typeUserRepository.findAll();
    }

    @GetMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TypeUser getTypeUser(@PathVariable int id) {
        logger.info("Sending Type User with id " + id);
        Optional<TypeUser> _typeUser = typeUserRepository.findById(id);
        if (!_typeUser.isPresent()) throw
                new NotFoundException("" + id, "User", "id");
        else
            return _typeUser.get();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public TypeUser saveTypeUser(@RequestBody TypeUser typeUser) {
        TypeUser savedTypeUser = typeUserRepository.save(typeUser);
        logger.info("Saving type user with id " + savedTypeUser.getId());
        return savedTypeUser;
    }

    @DeleteMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteTypeUser(@PathVariable int id) {
        logger.info("Deleting type user with id " + id);
        Optional<TypeUser> _typeUser = typeUserRepository.findById(id);
        if (!_typeUser.isPresent()) throw
                new NotFoundException("" + id, "Type user", "id");
        else
            typeUserRepository.deleteById(id);
            return new Response("Deleted type user with id " + id, null);
    }
}
