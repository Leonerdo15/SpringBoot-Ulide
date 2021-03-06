package com.iade.ulide.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.iade.ulide.models.Tag;
import com.iade.ulide.models.exceptions.NotFoundException;
import com.iade.ulide.models.exceptions.Response;
import com.iade.ulide.models.repositories.TagRepository;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/tags")
public class TagController {
    private final Logger logger = LoggerFactory.getLogger(TagController.class);

    @Autowired
    private TagRepository tagRepository;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Tag> getTags() {
        logger.info("Sending all tags !!!");
        return tagRepository.findAll();
    }

    @GetMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Tag getTag(@PathVariable int id) {
        logger.info("Sending tag with id " + id);
        Optional<Tag> _tag = tagRepository.findById(id);
        if (!_tag.isPresent()) throw
                new NotFoundException("" + id, "Tag", "id");
        else
            return _tag.get();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Tag saveTag(@RequestBody Tag tag) {
        Tag savedTag= tagRepository.save(tag);
        logger.info("Saving tag with id " + savedTag.getId());
        return savedTag;
    }

    @DeleteMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteTag(@PathVariable int id) {
        logger.info("Deleting tag with id " + id);
        Optional<Tag> _tag = tagRepository.findById(id);
        if (!_tag.isPresent()) throw
                new NotFoundException("" + id, "Tag", "id");
        else
            tagRepository.deleteById(id);
            return new Response("Deleted tag with id " + id, null);
    }

    @GetMapping(path = "/spot/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Tag> getTagsFromSpotId(@PathVariable int id) {
        logger.info("Getting all tags from Spot id " + id);
        return tagRepository.getTagsFromSpotId(id);
    }

    @GetMapping(path = "/user/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Tag> getTagsFromUserId(@PathVariable int id) {
        logger.info("Getting all tags from User id " + id);
        return tagRepository.getTagsFromUserId(id);
    }
}
