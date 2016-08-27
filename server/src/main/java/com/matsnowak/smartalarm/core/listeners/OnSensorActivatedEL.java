package com.matsnowak.smartalarm.core.listeners;

import com.google.common.collect.Lists;
import com.matsnowak.smartalarm.core.EventListener;
import com.matsnowak.smartalarm.core.Platform;
import com.matsnowak.smartalarm.core.events.SensorActivatedEvent;
import com.matsnowak.smartalarm.model.ArmedZone;
import com.matsnowak.smartalarm.model.Sensor;
import com.matsnowak.smartalarm.repositories.ArmedZoneRepository;
import com.matsnowak.smartalarm.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Nowak on 27.08.2016.
 */
public class OnSensorActivatedEL implements EventListener<SensorActivatedEvent> {

    @Autowired
    Platform platform;

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    ArmedZoneRepository armedZoneRepository;

    @Override
    public void listen(SensorActivatedEvent event) {

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
