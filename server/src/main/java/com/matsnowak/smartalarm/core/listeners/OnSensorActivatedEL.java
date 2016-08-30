package com.matsnowak.smartalarm.core.listeners;

import com.google.common.collect.Lists;
import com.matsnowak.smartalarm.core.EventListener;
import com.matsnowak.smartalarm.core.EventService;
import com.matsnowak.smartalarm.core.Platform;
import com.matsnowak.smartalarm.core.events.SensorActivatedEvent;
import com.matsnowak.smartalarm.core.events.ZoneArmedEvent;
import com.matsnowak.smartalarm.model.ArmedZone;
import com.matsnowak.smartalarm.model.Sensor;
import com.matsnowak.smartalarm.repositories.ArmedZoneRepository;
import com.matsnowak.smartalarm.repositories.SensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Nowak on 27.08.2016.
 */

@Component
public class OnSensorActivatedEL implements EventListener<SensorActivatedEvent> {
    private static Logger logger = LoggerFactory.getLogger(OnSensorActivatedEL.class);

    @Autowired
    Platform platform;

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    ArmedZoneRepository armedZoneRepository;

    @Autowired
    EventService eventService;

    @PostConstruct
    private void init() {
        eventService.register(this, SensorActivatedEvent.class);
    }

    @Override
    public void listen(SensorActivatedEvent event) {
        logger.debug("Sensor activated event handled " + event);

        Sensor activatedSensor = sensorRepository.findOne(event.getSensorId());
        List<ArmedZone> armedZones = Lists.newLinkedList(armedZoneRepository.findAll());

        List<ArmedZone> armedZonesContainingSensor = armedZones.stream()
                .filter(armedZone -> armedZone.getZone().getSensors().contains(activatedSensor))
                .collect(Collectors.toList());

        armedZonesContainingSensor.forEach(armedZone -> {
            platform.enableSignallers(armedZone.getZone().getSignallers());
        });
    }
}
