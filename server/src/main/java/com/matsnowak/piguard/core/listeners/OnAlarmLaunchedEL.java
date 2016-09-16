package com.matsnowak.piguard.core.listeners;

import com.matsnowak.piguard.core.EventListener;
import com.matsnowak.piguard.core.EventService;
import com.matsnowak.piguard.core.Platform;
import com.matsnowak.piguard.core.events.AlarmLaunchedEvent;
import com.matsnowak.piguard.model.ArmedZone;
import com.matsnowak.piguard.repositories.ArmedZoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by Mateusz Nowak on 17.09.2016.
 */
public class OnAlarmLaunchedEL implements EventListener<AlarmLaunchedEvent> {
    private static Logger logger = LoggerFactory.getLogger(OnAlarmLaunchedEL.class);

    @Autowired
    EventService eventService;

    @Autowired
    ArmedZoneRepository armedZoneRepository;

    @Autowired
    Platform platform;

    @PostConstruct
    private void init() {
        eventService.register(this, AlarmLaunchedEvent.class);
    }

    @Override
    public void listen(AlarmLaunchedEvent event) {
        logger.debug("Alarm launched event handled " + event);

        ArmedZone armedZone = armedZoneRepository.findOne(event.getArmedZoneId());
        if (armedZone == null) {
            logger.error("Armed zone with id " + event.getArmedZoneId() + " not exists!");
            return;
        }

        platform.enableSignallers(armedZone.getZone().getSignallers());
    }
}
