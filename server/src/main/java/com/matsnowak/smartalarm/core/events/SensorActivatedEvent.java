package com.matsnowak.smartalarm.core.events;

import com.matsnowak.smartalarm.core.Event;
import com.matsnowak.smartalarm.core.EventType;
import com.matsnowak.smartalarm.model.Sensor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by Mateusz Nowak on 17.08.2016.
 */
public class SensorActivatedEvent extends Event {
    private Integer sensorId;

    public SensorActivatedEvent(Integer sensorId) {
        super();
        this.sensorId = sensorId;
    }
    @Override
    public EventType getEventType() {
        return EventType.SENSOR_ACTIVATED;
    }

    public Integer getSensorId() {
        return sensorId;
    }

    @Override
    public String toString() {
        return "SensorActivatedEvent{" +
                "sensorId=" + sensorId +
                "} " + super.toString();
    }
}
