package com.matsnowak.smartalarm.core;

import com.matsnowak.smartalarm.model.Sensor;
import com.matsnowak.smartalarm.model.Slot;
import com.matsnowak.smartalarm.model.SlotState;
import com.matsnowak.smartalarm.model.Zone;
import com.pi4j.io.gpio.Pin;

import java.util.List;

/**
 * Created by Mateusz Nowak on 18.08.2016.
 */
public interface Platform {
    void update(Slot slot);

    void startMonitoring(Zone zone);

    void stopMonitoring(Zone zone);

}
