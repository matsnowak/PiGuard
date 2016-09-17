package com.matsnowak.piguard.core;

import com.matsnowak.piguard.core.events.SensorActivatedEvent;
import com.matsnowak.piguard.core.events.ZoneArmedEvent;
import com.matsnowak.piguard.core.events.ZoneDisarmedEvent;

import java.time.LocalDateTime;

/**
 * Created by Mateusz Nowak on 18.08.2016.
 */
public class Events {

    public static SensorActivatedEvent newSlotActivatedEvent(Integer sensorId) {
        return new SensorActivatedEvent(sensorId);
    }

    public static ZoneArmedEvent newZoneArmedEvent(Integer zoneId, Integer delayInSeconds) {
        return new ZoneArmedEvent(zoneId, delayInSeconds);
    }

    public static ZoneDisarmedEvent newZoneDisarmedEvent(Integer zoneId) {
        return new ZoneDisarmedEvent(zoneId);
    }


}
