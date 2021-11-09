package com.iade.ulide.controllers;

import com.iade.ulide.models.Achievement;
import com.iade.ulide.models.exceptions.NotFoundException;
import com.iade.ulide.models.exceptions.Response;
import com.iade.ulide.models.repositories.AchievementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(path = "/api/achievements")
public class AchievementController {


    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AchievementRepository achievementRepository;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Achievement> getAchievements() {
        logger.info("Sending all achievements!!!");
        return achievementRepository.findAll();
    }

    @GetMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Achievement getAchievement(@PathVariable int id) {

        logger.info("Sending achievemt with id " + id);
        Optional<Achievement> _achievement = achievementRepository.findById(id);
        if (!_achievement.isPresent()) throw
                new NotFoundException("" + id, "Achievement", "id");
        else
            return _achievement.get();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Achievement saveAchievement(@RequestBody Achievement achievement) {
        Achievement savedAchievement = achievementRepository.save(achievement);
        logger.info("Saving achievement with id " + savedAchievement.getId());
        return savedAchievement;
    }

    @DeleteMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteAchievement(@PathVariable int id) {
        logger.info("Deleting achievement with id " + id);
        Optional<Achievement> _achievement = achievementRepository.findById(id);
        if (!_achievement.isPresent()) throw
                new NotFoundException("" + id, "Achievement", "id");
        else
            achievementRepository.deleteById(id);
            return new Response("Deleted achievement with id " + id, null);
    }
}
