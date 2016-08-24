package com.matsnowak.smartalarm.core.events;

import com.matsnowak.smartalarm.core.Event;
import com.matsnowak.smartalarm.core.EventType;

import java.time.LocalDateTime;

/**
 * Created by Mateusz Nowak on 24.08.2016.
 */
public class ZoneArmedEvent extends Event {
    private Integer zoneId;
    private LocalDateTime startGuardFrom;

    public ZoneArmedEvent(Integer zoneId, LocalDateTime startGuardFrom) {
        super();
        this.zoneId = zoneId;
        this.startGuardFrom = startGuardFrom;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public LocalDateTime getStartGuardFrom() {
        return startGuardFrom;
    }

    @Override
    public EventType getEventType() {
        return EventType.ZONE_ARMED;
    }

    @Override
    public String toString() {
        return "ZoneArmedEvent{" +
                "zoneId=" + zoneId +
                ", startGuardFrom=" + startGuardFrom +
                "} " + super.toString();
    }
}
