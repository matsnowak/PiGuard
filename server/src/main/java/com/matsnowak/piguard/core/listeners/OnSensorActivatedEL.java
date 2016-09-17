package com.matsnowak.piguard.core.listeners;

import com.google.common.collect.Lists;
import com.matsnowak.piguard.core.EventListener;
import com.matsnowak.piguard.core.EventService;
import com.matsnowak.piguard.core.Platform;
import com.matsnowak.piguard.core.events.AlarmScheduledEvent;
import com.matsnowak.piguard.core.events.SensorActivatedEvent;
import com.matsnowak.piguard.model.ArmedZone;
import com.matsnowak.piguard.model.Sensor;
import com.matsnowak.piguard.model.Settings;
import com.matsnowak.piguard.repositories.ArmedZoneRepository;
import com.matsnowak.piguard.repositories.SensorRepository;
import com.matsnowak.piguard.repositories.SettingsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
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

    @Autowired
    SettingsRepository settingsRepository;

    @PostConstruct
    private void init() {
        eventService.register(this, SensorActivatedEvent.class);
    }

    @Override
    public void listen(SensorActivatedEvent event) {
        logger.debug("Sensor activated event handled " + event);

        Sensor activatedSensor = sensorRepository.findOne(event.getSensorId());
        List<ArmedZone> armedZones = Lists.newLinkedList(armedZoneRepository.findAll());
        logger.debug("armedZones " + armedZones);
        List<ArmedZone> armedZonesContainingSensor = armedZones.stream()
                .filter(armedZone -> armedZone.getZone().getSensors().contains(activatedSensor))
                .filter(armedZone -> event.getCreated().isAfter(armedZone.getStartGuardFrom()))
                .collect(Collectors.toList());

        Set<Integer> armedZonesIds = armedZonesContainingSensor.stream()
                .map(armedZone -> armedZone.getId())
                .collect(Collectors.toSet());

        if (!armedZonesIds.isEmpty()) {
            Settings settings = settingsRepository.findOne(1);
            if (settings == null) {
                logger.error("Settings is null");
                return;
            }
            eventService.publish(new AlarmScheduledEvent(event.getSensorId(), armedZonesIds, settings.getDisarmDelay() ));
        }
    }
}
