package com.pluralsight.conference_demo.controllers;

import com.pluralsight.conference_demo.models.Session;
import com.pluralsight.conference_demo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions") //To tell the router what the mapping url is: all requests to that url will be sent to this controller
public class SessionsController {

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping //To call this endpoint, the GET verb must be used
    public List<Session> list() {
        return sessionRepository.findAll(); //Query all the sessions in the DB and return as a list of objects
    }

    //Method to get a specific session by ID
    @GetMapping
    @RequestMapping("{id}") //Added to the url of the class
    public Session get(@PathVariable Long id) {
        return sessionRepository.getOne(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //Specify the exact response to occur when the method finishes
    public Session create(@RequestBody final Session session){ //Take Json payload and put it in Session object
        return sessionRepository.saveAndFlush(session); //Save in repo and then Flush to commit the change
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE) //@Verbs different than GET and POST must be specified in the @RequestMapping attribute
    public void delete(@PathVariable long id){
        //TODO: Also need to check for children records before deleting (delete with cascades)
        sessionRepository.deleteById(id);
    }

    //NB on the choice of the verb: PUT usually requests all the attributes (replaces what is not passed with NULL), PATCH only a part of them
    @RequestMapping(value = "{id}", method = RequestMethod.PUT) //We require the id in the url, and the method is PUT
    public Session update(@PathVariable Long id, @RequestBody Session session){
        //because this is a PUT, we expect all attributes are passed in. A PATCH would only need what ...
        //TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
        Session existingSession = sessionRepository.getOne(id); //Find the report to update by id
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }

}
