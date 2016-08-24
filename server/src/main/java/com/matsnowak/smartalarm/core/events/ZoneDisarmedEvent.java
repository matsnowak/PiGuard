package com.matsnowak.smartalarm.core.events;

import com.matsnowak.smartalarm.core.Event;
import com.matsnowak.smartalarm.core.EventType;

import java.time.LocalDateTime;

/**
 * Created by Mateusz Nowak on 24.08.2016.
 */
public class ZoneDisarmedEvent extends Event{
    private Integer zoneId;

    public ZoneDisarmedEvent(Integer zoneId) {
        super();
        this.zoneId = zoneId;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    @Override
    public EventType getEventType() {
        return EventType.ZONE_DISARMED;
    }

    @Override
    public String toString() {
        return "ZoneDisarmedEvent{" +
                "zoneId=" + zoneId +
                "} " + super.toString();
    }
}
