package com.pluralsight.conference_demo.controllers;

import com.pluralsight.conference_demo.models.Speaker;
import com.pluralsight.conference_demo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers") //To tell the router what the mapping url is: all requests to that url will be sent to this controller
public class SpeakersController {

    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping //To call this endpoint, the GET verb must be used
    public List<Speaker> list() {
        return speakerRepository.findAll(); //Query all the sessions in the DB and return as a list of objects
    }

    //Method to get a specific session by ID
    @GetMapping
    @RequestMapping("{id}") //Added to the url of the class
    public Speaker get(@PathVariable Long id) {
        return speakerRepository.getOne(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //Specify the exact response to occur when the method finishes
    public Speaker create(@RequestBody final Speaker session){
        return speakerRepository.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id){
        //TODO: Also need to check for children records before deleting (delete with cascades)
        speakerRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT) //We require the id in the url, and the method is PUT
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker){
        //TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
        Speaker existingSpeaker = speakerRepository.getOne(id); //Find the report to update by id
        BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
        return speakerRepository.saveAndFlush(existingSpeaker);
    }

}
