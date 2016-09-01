package com.matsnowak.piguard.repositories;

import com.matsnowak.piguard.core.EventService;
import com.matsnowak.piguard.core.Events;
import com.matsnowak.piguard.core.events.ZoneArmedEvent;
import com.matsnowak.piguard.core.events.ZoneDisarmedEvent;
import com.matsnowak.piguard.model.ArmedZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Nowak on 24.08.2016.
 */

@RepositoryEventHandler
@Component
public class ArmedZoneHandler {

    @Autowired
    EventService eventService;

    @HandleAfterCreate
    public void afterCreate(ArmedZone az) {
        ZoneArmedEvent event = Events.newZoneArmedEvent(az.getZone().getId(), az.getStartGuardFrom());
        eventService.publish(event);
    }

    @HandleAfterDelete
    public void afterDelete(ArmedZone az) {
        ZoneDisarmedEvent event = Events.newZoneDisarmedEvent(az.getZone().getId());
        eventService.publish(event);
    }
}
