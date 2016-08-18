package com.matsnowak.smartalarm.core;

import com.matsnowak.smartalarm.core.events.SensorActivatedEvent;
import com.matsnowak.smartalarm.model.Sensor;
import com.matsnowak.smartalarm.model.SlotAddress;
import com.pi4j.io.gpio.Pin;

/**
 * Created by Mateusz Nowak on 18.08.2016.
 */
public class Events {

    public static SensorActivatedEvent newSlotActivatedEvent(Sensor sensor) {
        return new SensorActivatedEvent(sensor);
    }
}
