package com.matsnowak.piguard.repositories;

import com.matsnowak.piguard.core.EventService;
import com.matsnowak.piguard.core.Events;
import com.matsnowak.piguard.core.events.ZoneArmedEvent;
import com.matsnowak.piguard.core.events.ZoneDisarmedEvent;
import com.matsnowak.piguard.model.ArmedZone;
import com.matsnowak.piguard.model.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * Created by Mateusz Nowak on 24.08.2016.
 */

@RepositoryEventHandler
@Component
public class ArmedZoneHandler {

    private static final Logger logger = LoggerFactory.getLogger(ArmedZoneHandler.class);

    @Autowired
    EventService eventService;

    @Autowired
    SettingsRepository settingsRepository;

    @HandleAfterCreate
    public void afterCreate(ArmedZone az) {
        Integer delay = getDelay();
        ZoneArmedEvent event = Events.newZoneArmedEvent(az.getZone().getId(), delay);
        eventService.publish(event);
    }

    @HandleBeforeCreate
    public void beforeCreate(ArmedZone az) {
        LocalDateTime now = LocalDateTime.now();
        Integer delay = getDelay();

        LocalDateTime startGuardFrom = now.plus(delay, ChronoUnit.SECONDS);
        az.setStartGuardFrom(startGuardFrom);
    }

    @HandleAfterDelete
    public void afterDelete(ArmedZone az) {
        ZoneDisarmedEvent event = Events.newZoneDisarmedEvent(az.getZone().getId());
        eventService.publish(event);
    }

    private Integer getDelay() {
        Settings settings = settingsRepository.findOne(1);
        Integer delay = 30; // TODO Default value
        if (settings == null || settings.getExitDelay() == null) {
            logger.warn("Settings does not exists");
        } else {
            delay = settings.getExitDelay();
        }

        return delay;
    }

}
