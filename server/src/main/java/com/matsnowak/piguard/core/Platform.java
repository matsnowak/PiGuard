package com.matsnowak.piguard.core;

import com.matsnowak.piguard.model.*;

import java.util.Set;

/**
 * Created by Mateusz Nowak on 18.08.2016.
 */
public interface Platform {

    void startMonitoring(Zone zone);

    void stopMonitoring(Zone zone);

    void enableSignallers(Set<Signaller> signallers);
}
