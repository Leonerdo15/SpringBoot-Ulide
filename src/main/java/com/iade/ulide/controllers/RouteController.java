package com.iade.ulide.controllers;

import com.iade.ulide.models.Spot;
import com.iade.ulide.models.exceptions.Response;
import com.iade.ulide.models.repositories.SpotRepository;
import com.iade.ulide.models.views.RouteView;
import com.iade.ulide.models.weak.RoutesSpots;
import com.iade.ulide.models.weak.repositories.FavRoutesRepository;
import com.iade.ulide.models.weak.repositories.RoutesSpotsRepository;
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

    @Autowired
    private RoutesSpotsRepository routesSpotsRepository;

    @Autowired
    private SpotRepository spotRepository;

    @Autowired
    private FavRoutesRepository favRouteRepository;

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
        logger.info("Deleting route with id " + id);
        Optional<Route> _route = routeRepository.findById(id);
        if (!_route.isPresent()) throw
                new NotFoundException("" + id, "Route", "id");
        else
            routeRepository.deleteById(id);
            return new Response("Deleted route with id " + id, null);
    }

    // Not working
    @GetMapping(path = "/popular", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Route> getAllRoutesByPopularity() {
        logger.info("Sending all Popular routes order!!!");
        return routeRepository.findAllRoutesSortedByPopularity();
    }

    @GetMapping(path = "/bio/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<String> getRouteBioById(@PathVariable int id) {
        logger.info("Sending bio from route id: " + id);
        return routeRepository.findRtBioById(id);
    }

    @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Route> searchRoutes(@RequestParam(value = "name", defaultValue = "") String name,
                                        @RequestParam(value = "distMin", defaultValue = "min") String distMin,
                                        @RequestParam(value = "distMax", defaultValue = "max") String distMax) {
        logger.info("Sending routes with name like "+name+ "and distance between " +distMin+ " and " +distMax);
        double _distMin = 0;
        double _distMax = Double.MAX_VALUE;
        try { _distMin = Double.parseDouble(distMin); }
        catch (NumberFormatException e) {}
        try { _distMax = Double.parseDouble(distMax); }
        catch (NumberFormatException e) {}
        return routeRepository.findByRtNameContainingAndRtDistBetween(name, _distMin, _distMax);
    }

    @GetMapping(path = "/spots", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<RoutesSpots> AllSpotsRoutes(){
        return routesSpotsRepository.findAll();
    }

    @GetMapping(path = "/{id:[0-9]+}/spots", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Spot> findSpotsRoute(@PathVariable int id) {
        logger.info("Sending alls spots with route id " + id);
        return spotRepository.findRouteSpots(id);
    }

    @GetMapping(path = "/avg", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<RouteView> allRouteAvg() {
        logger.info("Sending the average of the all routes");
        return routeRepository.avgAllRoutes();
    }

    @GetMapping(path = "/fav/user/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Route> findFavRouteByUserId(@PathVariable int id) {
        logger.info("Sending favorite route by user id" + id);
        return favRouteRepository.findFavRouteByUserId(id);
    }
}
