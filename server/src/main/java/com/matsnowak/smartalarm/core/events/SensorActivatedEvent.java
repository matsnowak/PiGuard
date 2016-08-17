package com.matsnowak.smartalarm.core.events;

import com.matsnowak.smartalarm.core.Event;
import com.matsnowak.smartalarm.model.Sensor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by Mateusz Nowak on 17.08.2016.
 */
public class SensorActivatedEvent implements Event {

    private UUID uuid;
    private LocalDateTime created;
    private Sensor sensor;

    @Override
    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public SensorActivatedEvent() {
        this.uuid = UUID.randomUUID();
    }
}
