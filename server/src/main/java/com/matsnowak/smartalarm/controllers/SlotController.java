package com.matsnowak.smartalarm.controllers;

import com.matsnowak.smartalarm.main.ApiUrls;
import com.matsnowak.smartalarm.model.Slot;
import com.matsnowak.smartalarm.model.SlotAddress;
import com.matsnowak.smartalarm.model.SlotMode;
import com.matsnowak.smartalarm.repositories.SlotRepository;
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
    private SlotRepository slotRepository;


    @RequestMapping(method = RequestMethod.PUT, value = "/{id}", path = "/{id}")
    public ResponseEntity<?> preventsPutOnSlotEntity(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> preventsPOSTOnSlotsCollection() {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}", path = "/{id}")
    public ResponseEntity<Slot> updateSlotState(@PathVariable("id") Integer id, @RequestBody Slot updatedSlot) {
        Slot fromRepo = slotRepository.findOne(id);
        if (fromRepo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        SlotAddress newAddress = updatedSlot.getAddress();
        if (newAddress != null) {
            if (!newAddress.equals(fromRepo.getAddress())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        SlotMode newMode = updatedSlot.getMode();
        if (newMode != null) {
            fromRepo.setMode(newMode);
            return new ResponseEntity<>(slotRepository.save(fromRepo), HttpStatus.OK);
        }

        return new ResponseEntity<>(updatedSlot, HttpStatus.BAD_REQUEST);
    }


}
