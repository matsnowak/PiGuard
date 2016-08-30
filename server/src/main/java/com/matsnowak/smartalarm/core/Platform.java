package com.matsnowak.smartalarm.core;

import com.matsnowak.smartalarm.model.*;
import com.pi4j.io.gpio.Pin;

import java.util.List;
import java.util.Set;

/**
 * Created by Mateusz Nowak on 18.08.2016.
 */
public interface Platform {

    void startMonitoring(Zone zone);

    void stopMonitoring(Zone zone);

    void enableSignallers(Set<Signaller> signallers);
}
