package com.iade.ulide.controllers;

import com.iade.ulide.models.UserAchievement;
import com.iade.ulide.models.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.iade.ulide.models.exceptions.Response;
import com.iade.ulide.models.repositories.UserAchievementRepository;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/userAchievements")
public class UserAchievementController {
    private final Logger logger = LoggerFactory.getLogger(UserAchievementController.class);

    @Autowired
    private UserAchievementRepository userAcRepository;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<UserAchievement> getUserAchievements() {
        logger.info("Sending all users achievements !!!");
        return userAcRepository.findAll();

    }

    @GetMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserAchievement getUserAchievement(@PathVariable int id) {
        logger.info("Sending users achievements with id " + id);
        Optional<UserAchievement>  _userAchievement = userAcRepository.findById(id);
        if (!_userAchievement.isPresent()) throw
                new NotFoundException("" + id, "User achievement", "id");
        else
            return _userAchievement.get();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserAchievement saveUserAchievement(@RequestBody UserAchievement userAchievement) {
        UserAchievement savedUserAchievement = userAcRepository.save(userAchievement);
        logger.info("Saving user achievement with id " + savedUserAchievement.getId());
        return savedUserAchievement;
    }

    @DeleteMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteUserAchievement(@PathVariable int id) {
        logger.info("Deleting user achievement with id " + id);
        Optional<UserAchievement>  _userAchievement = userAcRepository.findById(id);
        if (!_userAchievement.isPresent()) throw
                new NotFoundException("" + id, "User achievement", "id");
        else
            userAcRepository.deleteById(id);
            return new Response("Deleted user achievement with id " + id, null);
    }

    //Mudar istto com a materia do ?user=2
    @GetMapping(path = "user/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<UserAchievement> getAllUserAchievementsByUaUsId(@PathVariable int id) {
        logger.info("Sending all users achievements !!!");
        return userAcRepository.findUserAchievementsByUaUsId2(id);

    }
}
