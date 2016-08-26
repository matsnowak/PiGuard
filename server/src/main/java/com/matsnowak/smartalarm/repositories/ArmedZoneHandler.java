package com.matsnowak.smartalarm.repositories;

import com.matsnowak.smartalarm.core.EventService;
import com.matsnowak.smartalarm.core.Events;
import com.matsnowak.smartalarm.core.events.ZoneArmedEvent;
import com.matsnowak.smartalarm.core.events.ZoneDisarmedEvent;
import com.matsnowak.smartalarm.model.ArmedZone;
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
