package com.iade.ulide.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.iade.ulide.models.Spot;
import com.iade.ulide.models.exceptions.NotFoundException;
import com.iade.ulide.models.exceptions.Response;
import com.iade.ulide.models.repositories.SpotRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api/spots")
public class SpotController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SpotRepository spotRepository;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Spot> getSpots() {
        logger.info("Sending all spots !!!");
        return spotRepository.findAll();
    }

    @GetMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Spot getSpot(@PathVariable int id) {
        logger.info("Sending spot with id " + id);
        Optional<Spot> _spot = spotRepository.findById(id);
        if (!_spot.isPresent()) throw
                new NotFoundException("" + id, "Spot", "id");
        else
            return _spot.get();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Spot saveSpot(@RequestBody Spot spot) {
        Spot savedSpot = spotRepository.save(spot);
        logger.info("Saving spot with id " + savedSpot.getId());
        return savedSpot;
    }

    @DeleteMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteSpot(@PathVariable int id) {
        logger.info("Deleting spot with id " + id);
        Optional<Spot> _spot = spotRepository.findById(id);
        if (!_spot.isPresent()) throw
                new NotFoundException("" + id, "Spot", "id");
        else
            spotRepository.deleteById(id);
            return new Response("Deleted spot with id " + id, null);
    }

    @GetMapping(path = "/{id:[0-9]+}/average", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Double> averageSpot(@PathVariable int id) {
        logger.info("Getting average form spot id: " + id);
        return spotRepository.spotAverage(id);
    }

    @GetMapping(path = "/bio/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<String> getSpotBioById(@PathVariable int id) {
        logger.info("Sending bio from spot id: " + id);
        return spotRepository.findSpBioById(id);
    }

    @GetMapping(path = "/comments/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<List<String>> getSpotComments(@PathVariable int id) {
        logger.info("Sending all comments from spot id: " + id);
        return spotRepository.findAllSpotComments(id);
    }
}
