package com.iade.ulide.models.weak.controllers;

import com.iade.ulide.models.exceptions.NotFoundException;
import com.iade.ulide.models.exceptions.Response;
import com.iade.ulide.models.weak.RoutesSpots;
import com.iade.ulide.models.weak.repositories.RoutesSpotsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/routesSpots")
public class RoutesSpotsController {
    private final Logger logger = LoggerFactory.getLogger(RoutesSpotsController.class);

    @Autowired
    private RoutesSpotsRepository routesSpotsRepository;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<RoutesSpots> getRoutesSpots() {
        logger.info("Sending all spots from routes");
        return routesSpotsRepository.findAll();
    }

    @GetMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RoutesSpots getRoutesSpots(@PathVariable int id) {
        logger.info("Sending routes spots with id " + id);
        Optional<RoutesSpots> _routesSpots = routesSpotsRepository.findById(id);
        if (!_routesSpots.isPresent()) throw
                new NotFoundException("" + id, "Routes Spots", "id");
        else
            return _routesSpots.get();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public RoutesSpots saveRoutesSpots(@RequestBody RoutesSpots favSpots) {
        RoutesSpots savedRoutesSpot = routesSpotsRepository.save(favSpots);
        logger.info("Saving routes spots with id " + savedRoutesSpot.getId());
        return savedRoutesSpot;
    }

    @DeleteMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteRoutesSpots(@PathVariable int id) {
        logger.info("Deleting routes spots with id " + id);
        Optional<RoutesSpots> _routesSpots = routesSpotsRepository.findById(id);
        if (!_routesSpots.isPresent()) throw
                new NotFoundException("" + id, "Routes Spots", "id");
        else
            routesSpotsRepository.deleteById(id);
        return new Response("Deleted routes spots with id " + id, null);
    }
}
