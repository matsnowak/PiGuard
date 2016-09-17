package com.matsnowak.piguard.core.events;

import com.matsnowak.piguard.core.Event;
import com.matsnowak.piguard.core.EventType;

import java.time.LocalDateTime;

/**
 * Created by Mateusz Nowak on 24.08.2016.
 */
public class ZoneArmedEvent extends Event {
    private Integer zoneId;
    private Integer delayInSeconds;

    public ZoneArmedEvent(Integer zoneId, Integer delayInSeconds) {
        super();
        this.zoneId = zoneId;
        this.delayInSeconds = delayInSeconds;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public Integer getDelayInSeconds() {
        return delayInSeconds;
    }

    @Override
    public EventType getEventType() {
        return EventType.ZONE_ARMED;
    }

    @Override
    public String toString() {
        return "ZoneArmedEvent{" +
                "zoneId=" + zoneId +
                ", delayInSeconds=" + delayInSeconds +
                "} " + super.toString();
    }
}
