package com.matsnowak.smartalarm.platforms.stub;

import com.matsnowak.smartalarm.core.Platform;
import com.matsnowak.smartalarm.model.Signaller;
import com.matsnowak.smartalarm.model.Slot;
import com.matsnowak.smartalarm.model.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

/**
 * Created by Mateusz Nowak on 23.08.2016.
 */
public class StubPlatform implements Platform {
    private final static Logger logger = LoggerFactory.getLogger(StubPlatform.class);


    @Override
    public void update(Slot slot) {
        logger.debug("Updated slot " + slot);

    }

    @Override
    public void startMonitoring(Zone zone) {
        logger.debug("Monitoring started " + zone);

    }

    @Override
    public void stopMonitoring(Zone zone) {
        logger.debug("Monitoring stopped " + zone);
    }

    @Override
    public void enableSignallers(Set<Signaller> signallers) {
        logger.debug("Enable signallers " + signallers);
    }
}
