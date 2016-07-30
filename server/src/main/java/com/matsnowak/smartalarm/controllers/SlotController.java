package com.matsnowak.smartalarm.controllers;

import com.matsnowak.smartalarm.main.ApiUrls;
import com.matsnowak.smartalarm.model.CommunicationSlot;
import com.matsnowak.smartalarm.model.CommunicationSlotAddress;
import com.matsnowak.smartalarm.model.CommunicationSlotState;
import com.matsnowak.smartalarm.repositories.CommunicationSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Mateusz Nowak on 30.07.2016.
 */

@RepositoryRestController
@RequestMapping(path = ApiUrls.API_SLOTS, value = ApiUrls.API_SLOTS)
public class SlotController {

    @Autowired
    private CommunicationSlotRepository slotRepository;


    @RequestMapping(method = RequestMethod.PUT, value = "/{id}", path = "/{id}")
    public ResponseEntity<?> preventsPutOnSlotEntity(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> preventsPOSTOnSlotsCollection() {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}", path = "/{id}")
    public ResponseEntity<CommunicationSlot> updateSlotState(@PathVariable("id") Integer id, @RequestBody CommunicationSlot updatedSlot) {
        CommunicationSlot fromRepo = slotRepository.findOne(id);
        if (fromRepo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CommunicationSlotAddress newAddress = updatedSlot.getAddress();
        if (newAddress != null) {
            if (!newAddress.equals(fromRepo.getAddress())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        CommunicationSlotState newState = updatedSlot.getState();
        if (newState != null) {
            fromRepo.setState(newState);
            return new ResponseEntity<>(slotRepository.save(fromRepo), HttpStatus.OK);
        }

        return new ResponseEntity<>(updatedSlot, HttpStatus.BAD_REQUEST);
    }


}
