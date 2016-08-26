package com.matsnowak.smartalarm.core.listeners;

import com.matsnowak.smartalarm.core.EventListener;
import com.matsnowak.smartalarm.core.EventService;
import com.matsnowak.smartalarm.core.Platform;
import com.matsnowak.smartalarm.core.events.ZoneArmedEvent;
import com.matsnowak.smartalarm.model.Zone;
import com.matsnowak.smartalarm.repositories.ZoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.PostConstruct;

/**
 * Created by Mateusz Nowak on 26.08.2016.
 */

@Component
public class OnZoneArmedEL implements EventListener<ZoneArmedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(OnZoneArmedEL.class);

    @Autowired
    private EventService eventService;

    @Autowired
    private Platform platform;

    @Autowired
    private ZoneRepository zoneRepository;

    @PostConstruct
    private void init() {
        eventService.register(this, ZoneArmedEvent.class);
    }

    @Override
    public void listen(ZoneArmedEvent event) {
        Zone armedZone = zoneRepository.findOne(event.getZoneId());
        if (armedZone == null) {
            logger.warn("Armed zone not exists: " + event.getZoneId());
        }
        platform.startMonitoring(armedZone);
    }
}
