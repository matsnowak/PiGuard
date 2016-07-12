package com.matsnowak.smartalarm.main.controllers;

import com.matsnowak.smartalarm.main.exceptions.SensorException;
import com.matsnowak.smartalarm.main.services.SignallerService;
import com.matsnowak.smartalarm.model.Signaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Mateusz on 24.06.2016.
 */
public class SignallerController {

    @Autowired
    private SignallerService signallerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Signaller> findAll() {
        return signallerService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Signaller findById(@PathVariable Integer id) {
        return signallerService.find(id).orElse(null);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Signaller add(@RequestBody Signaller newSignaller) throws SensorException {
        return signallerService.add(newSignaller);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(Signaller signaller) {
        signallerService.delete(signaller);
    }
}
