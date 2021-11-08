package com.iade.ulide.controllers;

import com.iade.ulide.models.RouteEvaluation;
import com.iade.ulide.models.exceptions.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.iade.ulide.models.Route;
import com.iade.ulide.models.exceptions.NotFoundException;
import com.iade.ulide.models.repositories.RouteRepository;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/routes")
public class RouteController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private RouteRepository routeRepository;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Route> getRoutes() {
        logger.info("Sending all routes!!!");
        return routeRepository.findAll();
    }

    @GetMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Route getRoute(@PathVariable int id) {
        logger.info("Sending route with id " + id);
        Optional<Route> _route = routeRepository.findById(id);
        if (!_route.isPresent()) throw
                new NotFoundException("" + id, "Route", "id");
        else
            return _route.get();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Route saveRoute(@RequestBody Route route) {
        Route savedRoute = routeRepository.save(route);
        logger.info("Saving route with id " + savedRoute.getId());
        return savedRoute;
    }

    @DeleteMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteRoute(@PathVariable int id) {
        logger.info("Deleting route evaluation with id " + id);
        routeRepository.deleteById(id);
        return new Response("Deleted route evaluation with id " + id, null);
    }
}
