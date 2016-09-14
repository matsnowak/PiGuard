package com.matsnowak.piguard.model.projections;

import com.matsnowak.piguard.model.ArmedZone;
import com.matsnowak.piguard.model.Zone;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

/**
 * Created by Mateusz Nowak on 14.09.2016.
 */
@Projection(name = "inline", types = {ArmedZone.class})
public interface ArmedZoneInline {
    Integer getId();

    Zone getZone();

    LocalDateTime getStartGuardFrom();
}
