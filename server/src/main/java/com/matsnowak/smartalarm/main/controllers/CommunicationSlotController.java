package com.matsnowak.smartalarm.main.controllers;

import com.matsnowak.smartalarm.main.services.CommunicationSlotService;
import com.matsnowak.smartalarm.model.CommunicationSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by Mateusz on 19.06.2016.
 */

@RestController()
@RequestMapping("slots")
public class CommunicationSlotController {

    @Autowired
    CommunicationSlotService communicationSlotService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<CommunicationSlot> getAllSLots() {
        return communicationSlotService.getAllSlots();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public CommunicationSlot getSlotById(@PathVariable Integer id) {
        return communicationSlotService.getSlotById(id).orElse(null);
    }


}
