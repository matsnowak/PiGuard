package com.matsnowak.piguard.core.listeners;

import com.matsnowak.piguard.core.EventListener;
import com.matsnowak.piguard.core.EventService;
import com.matsnowak.piguard.core.events.AlarmScheduledEvent;
import com.matsnowak.piguard.repositories.ArmedZoneRepository;
import com.matsnowak.piguard.core.AlarmScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by Mateusz Nowak on 16.09.2016.
 */
public class OnAlarmScheduledEL implements EventListener<AlarmScheduledEvent>{
    private static Logger logger = LoggerFactory.getLogger(OnAlarmScheduledEL.class);

    @Autowired
    EventService eventService;

    @Autowired
    ArmedZoneRepository armedZoneRepository;

    @Autowired
    AlarmScheduler scheduler;

    @PostConstruct
    private void init() {
        eventService.register(this, AlarmScheduledEvent.class);
    }

    @Override
    public void listen(AlarmScheduledEvent event) {
        logger.debug("Alarm scheduled event handled " + event);

        scheduler.scheduleAlarm(event.getArmedZonesIds(), event.getDelayInSeconds(), event.getTriggeredBySensorId());
    }
}
