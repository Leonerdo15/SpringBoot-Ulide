package com.iade.ulide.models.weak.controllers;

import com.iade.ulide.models.exceptions.NotFoundException;
import com.iade.ulide.models.exceptions.Response;
import com.iade.ulide.models.weak.FavSpots;
import com.iade.ulide.models.weak.repositories.FavSpotsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/favSpots")
public class FavSpotsController {
    private final Logger logger = LoggerFactory.getLogger(FavSpotsController.class);

    @Autowired
    private FavSpotsRepository favSpotsRepository;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<FavSpots> getFavSpots() {
        logger.info("Sending all favorite spots");
        return favSpotsRepository.findAll();
    }

    @GetMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FavSpots getFavSpot(@PathVariable int id) {
        logger.info("Sending favorite spot with id " + id);
        Optional<FavSpots> _favSpot = favSpotsRepository.findById(id);
        if (!_favSpot.isPresent()) throw
                new NotFoundException("" + id, "Favorite Spot", "id");
        else
            return _favSpot.get();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public FavSpots saveFavSpot(@RequestBody FavSpots favSpots) {
        FavSpots savedFavSpot = favSpotsRepository.save(favSpots);
        logger.info("Saving favorite spot with id " + savedFavSpot.getId());
        return savedFavSpot;
    }

    @DeleteMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteFavSpot(@PathVariable int id) {
        logger.info("Deleting favorite spot with id " + id);
        Optional<FavSpots> _favSpot = favSpotsRepository.findById(id);
        if (!_favSpot.isPresent()) throw
                new NotFoundException("" + id, "Favorite Spot", "id");
        else
            favSpotsRepository.deleteById(id);
        return new Response("Deleted favorite spot with id " + id, null);
    }

    @GetMapping(path = "/{usId:[0-9]+}/{spId:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer getFavSpotIdByUserAndSpot(@PathVariable int usId, @PathVariable int spId) {
        logger.info("Getting favorite spot id by usId " + usId + "and spId " + spId);
        return favSpotsRepository.getFavSpotIdByUserAndSpot(usId, spId);
    }

    @DeleteMapping(path = "/del/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response delFavSpot(@PathVariable int id) {
        logger.info("Deleting fav spot with id " + id);
        Optional<FavSpots> _favSpot = favSpotsRepository.findById(id);
        if (!_favSpot.isPresent()) throw
                new NotFoundException("" + id, "Favorite Spot", "id");
        else
            favSpotsRepository.deleteById(id);
        return new Response("Deleted favorite spot with id " + id, null);
    }
}
