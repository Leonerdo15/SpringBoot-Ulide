package com.iade.ulide.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.iade.ulide.models.SpotEvaluation;
import com.iade.ulide.models.exceptions.NotFoundException;
import com.iade.ulide.models.exceptions.Response;
import com.iade.ulide.models.repositories.SpotEvaluationRepository;
import com.iade.ulide.models.repositories.TagRepository;

import java.util.Optional;


@RestController
@RequestMapping(path = "/api/spotsEvaluations")
public class SpotEvaluationController {
    private final Logger logger = LoggerFactory.getLogger(TagRepository.class);

    @Autowired
    private SpotEvaluationRepository spotEvaRepository;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<SpotEvaluation> getSpotEvaluations() {
        logger.info("Sending all spot evaluations !!!");
        return spotEvaRepository.findAll();
    }

    @GetMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SpotEvaluation getSpotEvaluation(@PathVariable int id) {
        logger.info("Sending spot evaluation with id " + id);
        Optional<SpotEvaluation> _spotEvaluation = spotEvaRepository.findById(id);
        if (!_spotEvaluation.isPresent()) throw
                new NotFoundException("" + id, "Spot Evaluation", "id");
        else
            return _spotEvaluation.get();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public SpotEvaluation saveSpotEvaluation(@RequestBody SpotEvaluation spotEvaluation) {
        SpotEvaluation savedSpotEvaluation = spotEvaRepository.save(spotEvaluation);
        logger.info("Saving spot evaluation with id " + savedSpotEvaluation.getId());
        return savedSpotEvaluation;
    }

    @DeleteMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteSpotEvaluation(@PathVariable int id) {
        logger.info("Deleting spot evaluation with id " + id);
        spotEvaRepository.deleteById(id);
        return new Response("Deleted user with id " + id, null);
    }

}
