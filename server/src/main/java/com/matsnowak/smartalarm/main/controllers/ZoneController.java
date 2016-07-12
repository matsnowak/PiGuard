package com.matsnowak.smartalarm.main.controllers;

import com.matsnowak.smartalarm.main.exceptions.SensorException;
import com.matsnowak.smartalarm.main.services.ZoneService;
import com.matsnowak.smartalarm.model.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Mateusz on 30.06.2016.
 */
@RestController
@RequestMapping("zones")
public class ZoneController {

    @Autowired
    ZoneService zoneService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Zone> findAll() {
        return zoneService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Zone findById(@PathVariable Integer id) {
        return zoneService.find(id).orElse(null);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Zone add(@RequestBody Zone newZone) throws SensorException {
        return zoneService.add(newZone);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(Zone zone) {
        zoneService.delete(zone);
    }

}
