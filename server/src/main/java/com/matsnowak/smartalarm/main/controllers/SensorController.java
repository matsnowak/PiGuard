package com.matsnowak.smartalarm.main.controllers;

import com.matsnowak.smartalarm.main.exceptions.SensorException;
import com.matsnowak.smartalarm.main.services.SensorService;
import com.matsnowak.smartalarm.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Mateusz on 20.06.2016.
 */
@RestController
@RequestMapping("sensors")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Sensor> findAll() {
        return sensorService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Sensor findById(@PathVariable Integer id) {
        return sensorService.find(id).orElse(null);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Sensor add(@RequestBody Sensor newSensor) throws SensorException {
        return sensorService.add(newSensor);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(Sensor sensor) {
         sensorService.delete(sensor);
    }

    @RequestMapping(value ="example", method = RequestMethod.GET, produces = "application/json")
    public Sensor example() {
        return new Sensor("sensor1", SensorType.PIR, new CommunicationSlot(CommunicationSlotAddress.SLOT_1, CommunicationSlotState.INPUT));
    }
}
