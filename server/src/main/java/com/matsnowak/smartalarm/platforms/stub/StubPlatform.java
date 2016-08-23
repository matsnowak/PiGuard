package com.matsnowak.smartalarm.platforms.stub;

import com.matsnowak.smartalarm.core.Platform;
import com.matsnowak.smartalarm.model.Slot;

import java.util.List;

/**
 * Created by Mateusz Nowak on 23.08.2016.
 */
public class StubPlatform implements Platform {
    @Override
    public void startMonitoring(List<Slot> slotList) {
        System.out.println("StubPlatform -> Start monitoring ");
    }

    @Override
    public void stopMonitoring() {
        System.out.println("StubPlatform -> Stop monitoring ");

    }
}
