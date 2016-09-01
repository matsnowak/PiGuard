package com.matsnowak.smartalarm.controllers;

import com.matsnowak.smartalarm.main.ApiUrls;
import com.matsnowak.smartalarm.repositories.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Mateusz Nowak on 30.07.2016.
 */

@RepositoryRestController
@RequestMapping(path = ApiUrls.API_SLOTS, value = ApiUrls.API_SLOTS)
public class SlotController {

    @Autowired
    private SlotRepository slotRepository;


    @RequestMapping(method = RequestMethod.PUT, value = "/{id}", path = "/{id}")
    public ResponseEntity<?> preventsPutOnSlotEntity(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> preventsPOSTOnSlotsCollection() {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

}
