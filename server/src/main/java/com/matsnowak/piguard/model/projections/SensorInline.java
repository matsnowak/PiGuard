package com.matsnowak.piguard.model.projections;

import com.matsnowak.piguard.model.PullResistance;
import com.matsnowak.piguard.model.Sensor;
import com.matsnowak.piguard.model.Slot;
import com.matsnowak.piguard.model.TriggeringType;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by Mateusz Nowak on 14.09.2016.
 */
@Projection(name = "inline", types = {Sensor.class})
public interface SensorInline {
    Integer getId();

    String getName();

    Slot getSlot();

    TriggeringType getTriggeredOn();

    PullResistance getPullResistance();
}
