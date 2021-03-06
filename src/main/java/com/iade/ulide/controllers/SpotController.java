package com.iade.ulide.controllers;

import com.iade.ulide.models.SpotEvaluation;
import com.iade.ulide.models.repositories.SpotEvaluationRepository;
import com.iade.ulide.models.views.SpotView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.iade.ulide.models.Spot;
import com.iade.ulide.models.exceptions.NotFoundException;
import com.iade.ulide.models.exceptions.Response;
import com.iade.ulide.models.repositories.SpotRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/spots")
public class SpotController {
    private final Logger logger = LoggerFactory.getLogger(SpotController.class);

    @Autowired
    private SpotRepository spotRepository;

    @Autowired
    private SpotEvaluationRepository spotEvaluationRepository;

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

    @GetMapping(path = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSpoBytName(@PathVariable String name) {
        logger.info("Sending spot with id " + name);
        Optional<String> _spot = spotRepository.findBySpName(name);
        return _spot.orElse("0");
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

    @GetMapping(path = "/avg", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<SpotView> allSpotsAvg() {
        logger.info("Sending the average of all spots");
        return spotRepository.avgAllSpots();
    }

    @GetMapping(path = "/fav/user/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Spot> findFavSpotsFromUserId(@PathVariable int id) {
        logger.info("Sending all favorite spots from user id " + id);
        return spotRepository.findFavSpotsFromUserId(id);
    }

    @GetMapping(path = "/done/user/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Spot> findDoneSpotsFromUserId(@PathVariable int id) {
        logger.info("Sending all done spots from user id " + id);
        return spotRepository.findDoneSpotsFromUserId(id);
    }

    @GetMapping(path = "/eval/user/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Spot> findEvalSpotsFromUserId(@PathVariable int id) {
        logger.info("Sending all spots evaluations from user id " + id);
        return spotRepository.findEvalSpotsFromUserId(id);
    }

    @GetMapping(path = "/{id:[0-9]+}/eval", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<SpotEvaluation> findEvalBySpotId(@PathVariable int id) {
        logger.info("Sending all evaluations from spot id " + id);
        return spotEvaluationRepository.findBySpotId(id);
    }
}
