package com.matsnowak.piguard.model.projections;

import com.matsnowak.piguard.model.Sensor;
import com.matsnowak.piguard.model.Signaller;
import com.matsnowak.piguard.model.Zone;
import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

/**
 * Created by Mateusz Nowak on 14.09.2016.
 */
@Projection(name = "inline", types = {Zone.class})
public interface ZoneInline {
    Integer getId();

    String getName();

    Set<Sensor> getSensors();

    Set<Signaller> getSignallers();
}
