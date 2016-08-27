package com.matsnowak.smartalarm.core.listeners;

import com.matsnowak.smartalarm.core.EventListener;
import com.matsnowak.smartalarm.core.EventService;
import com.matsnowak.smartalarm.core.Platform;
import com.matsnowak.smartalarm.core.events.ZoneDisarmedEvent;
import com.matsnowak.smartalarm.model.Zone;
import com.matsnowak.smartalarm.repositories.ZoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Mateusz Nowak on 27.08.2016.
 */
@Component
public class OnZoneDisarmedEl implements EventListener<ZoneDisarmedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(OnZoneDisarmedEl.class);

    @Autowired
    private EventService eventService;

    @Autowired
    private Platform platform;

    @Autowired
    private ZoneRepository zoneRepository;

    @PostConstruct
    private void init() {
        eventService.register(this, ZoneDisarmedEvent.class);
    }

    @Override
    public void listen(ZoneDisarmedEvent event) {
        Zone disarmedZone = zoneRepository.findOne(event.getZoneId());
        if (disarmedZone == null) {
            logger.warn("Disarmed zone not exists: " + event.getZoneId());
            return;
        }

        platform.stopMonitoring(disarmedZone);
    }
}
