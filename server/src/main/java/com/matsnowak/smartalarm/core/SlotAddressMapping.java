package com.matsnowak.smartalarm.core;

import com.matsnowak.smartalarm.model.SlotAddress;

/**
 * Created by Mateusz Nowak on 18.08.2016.
 */
public interface SlotAddressMapping<T> {

    T map(SlotAddress slotAddress);

    SlotAddress map(T input);
}
