package com.matsnowak.smartalarm.core;

import com.matsnowak.smartalarm.core.events.SensorActivatedEvent;
import com.matsnowak.smartalarm.core.events.ZoneArmedEvent;
import com.matsnowak.smartalarm.core.events.ZoneDisarmedEvent;

import java.time.LocalDateTime;

/**
 * Created by Mateusz Nowak on 18.08.2016.
 */
public class Events {

    public static SensorActivatedEvent newSlotActivatedEvent(Integer sensorId) {
        return new SensorActivatedEvent(sensorId);
    }

    public static ZoneArmedEvent newZoneArmedEvent(Integer zoneId, LocalDateTime startGuardFrom) {
        return new ZoneArmedEvent(zoneId, startGuardFrom);
    }

    public static ZoneDisarmedEvent newZoneDisarmedEvent(Integer zoneId) {
        return new ZoneDisarmedEvent(zoneId);
    }


}
