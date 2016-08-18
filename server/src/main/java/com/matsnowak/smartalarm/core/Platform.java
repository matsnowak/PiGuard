package com.matsnowak.smartalarm.core;

import com.matsnowak.smartalarm.model.Slot;
import com.pi4j.io.gpio.Pin;

import java.util.List;

/**
 * Created by Mateusz Nowak on 18.08.2016.
 */
public interface Platform {
    void startMonitoring(List<Slot> slotList);

    void stopMonitoring();

}
