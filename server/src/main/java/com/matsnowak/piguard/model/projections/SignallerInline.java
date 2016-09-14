package com.matsnowak.piguard.model.projections;

import com.matsnowak.piguard.model.Signaller;
import com.matsnowak.piguard.model.Slot;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by Mateusz Nowak on 14.09.2016.
 */
@Projection(name = "inline", types = {Signaller.class})
public interface SignallerInline {
    Integer getId();

    String getName();

    Slot getSlot();
}
