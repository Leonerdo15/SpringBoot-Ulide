package com.iade.ulide.controllers;

import com.iade.ulide.models.TagType;
import com.iade.ulide.models.exceptions.NotFoundException;
import com.iade.ulide.models.exceptions.Response;
import com.iade.ulide.models.repositories.TagTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/tagTypes")
public class TagTypeController {
    private final Logger logger = LoggerFactory.getLogger(TagTypeController.class);

    @Autowired
    private TagTypeRepository tagTypeRepository;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<TagType> getTagTypes() {
        logger.info("Sending all Tag types !!!");
        return tagTypeRepository.findAll();
    }

    @GetMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TagType getTagType(@PathVariable int id) {
        logger.info("Sending Tag Type with id " + id);
        Optional<TagType> _tagType = tagTypeRepository.findById(id);
        if (!_tagType.isPresent()) throw
                new NotFoundException("" + id, "User", "id");
        else
            return _tagType.get();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public TagType saveTagType(@RequestBody TagType tagType) {
        TagType savedTagType = tagTypeRepository.save(tagType);
        logger.info("Saving Tag Type with id " + savedTagType.getId());
        return savedTagType;
    }

    @DeleteMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteTypeUser(@PathVariable int id) {
        logger.info("Deleting Tag Type with id " + id);
        Optional<TagType> _tagType = tagTypeRepository.findById(id);
        if (!_tagType.isPresent()) throw
                new NotFoundException("" + id, "TagType", "id");
        else
            tagTypeRepository.deleteById(id);
            return new Response("Deleted TagType with id " + id, null);
    }
}
