package com.matsnowak.smartalarm.core;

import com.matsnowak.smartalarm.model.*;
import com.pi4j.io.gpio.Pin;

import java.util.List;

/**
 * Created by Mateusz Nowak on 18.08.2016.
 */
public interface Platform {

    // TODO not used now
    void update(Slot slot);

    void startMonitoring(Zone zone);

    void stopMonitoring(Zone zone);

    void enableSignallers(List<Signaller> signallers);
}
