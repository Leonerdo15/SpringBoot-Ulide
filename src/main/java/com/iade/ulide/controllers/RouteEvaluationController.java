package com.iade.ulide.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.iade.ulide.models.RouteEvaluation;
import com.iade.ulide.models.exceptions.NotFoundException;
import com.iade.ulide.models.exceptions.Response;
import com.iade.ulide.models.repositories.RouteEvaluationRepository;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/routesEvaluations")
public class RouteEvaluationController {
    private final Logger logger = LoggerFactory.getLogger(RouteEvaluationController.class);

    @Autowired
    private RouteEvaluationRepository routeEvRepository;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<RouteEvaluation> getRouteEvaluations() {
        logger.info("Sending all route evaluations!!!");
        return routeEvRepository.findAll();
    }

    @GetMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RouteEvaluation getRouteEvaluation(@PathVariable int id) {
        logger.info("Sending route evaluation with id " + id);
        Optional<RouteEvaluation> _routeEvaluation = routeEvRepository.findById(id);
        if (!_routeEvaluation.isPresent()) throw
                new NotFoundException("" + id, "Route Evaluation", "id");
        else
            return _routeEvaluation.get();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public RouteEvaluation saveRouteEvaluation(@RequestBody RouteEvaluation routeEvaluation) {
        RouteEvaluation savedRouteEvaluation = routeEvRepository.save(routeEvaluation);
        logger.info("Saving route evaluation with id " + savedRouteEvaluation.getId());
        return savedRouteEvaluation;
    }

    @DeleteMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteRouteEvaluation(@PathVariable int id) {
        logger.info("Deleting route evaluation with id " + id);
        Optional<RouteEvaluation> _routeEvaluation = routeEvRepository.findById(id);
        if (!_routeEvaluation.isPresent()) throw
                new NotFoundException("" + id, "Route Evaluation", "id");
        else
            routeEvRepository.deleteById(id);
            return new Response("Deleted route evaluation with id " + id, null);
    }

    @GetMapping(path = "/user/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<RouteEvaluation> findByUserId(@PathVariable int id) {
        logger.info("Getting evaluations by user id " + id);
        return routeEvRepository.findByUserId(id);
    }
}
