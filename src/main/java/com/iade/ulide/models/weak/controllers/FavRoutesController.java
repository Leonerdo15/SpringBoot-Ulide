package com.iade.ulide.models.weak.controllers;

import com.iade.ulide.models.exceptions.NotFoundException;
import com.iade.ulide.models.exceptions.Response;
import com.iade.ulide.models.weak.FavRoutes;
import com.iade.ulide.models.weak.repositories.FavRoutesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/favRoutes")
public class FavRoutesController {
    private final Logger logger = LoggerFactory.getLogger(FavRoutesController.class);

    @Autowired
    private FavRoutesRepository favRoutesRepository;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<FavRoutes> getFavRoutes() {
        logger.info("Sending all favorite routes");
        return favRoutesRepository.findAll();
    }

    @GetMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FavRoutes getFavRoutes(@PathVariable int id) {
        logger.info("Sending favorite route with id " + id);
        Optional<FavRoutes> _favRoute = favRoutesRepository.findById(id);
        if (!_favRoute.isPresent()) throw
                new NotFoundException("" + id, "Favorite Route", "id");
        else
            return _favRoute.get();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public FavRoutes saveFavRoute(@RequestBody FavRoutes favRoutes) {
        FavRoutes savedFavRoute = favRoutesRepository.save(favRoutes);
        logger.info("Saving favorite route with id " + savedFavRoute.getId());
        return savedFavRoute;
    }

    @DeleteMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteFavRoute(@PathVariable int id) {
        logger.info("Deleting favorite route with id " + id);
        Optional<FavRoutes> _favRoute = favRoutesRepository.findById(id);
        if (!_favRoute.isPresent()) throw
                new NotFoundException("" + id, "Favorite Route", "id");
        else
            favRoutesRepository.deleteById(id);
        return new Response("Deleted favorite route with id " + id, null);
    }

    @GetMapping(path = "/{usId:[0-9]+}/{rtId:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer getFavRouteIdByUserAndRoute(@PathVariable int usId, @PathVariable int rtId) {
        logger.info("Getting favorite route id by usId " + usId + "and rtId " + rtId);
        return favRoutesRepository.getFavRouteIdByUserAndRoute(usId, rtId);
    }
}
