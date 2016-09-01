package com.matsnowak.piguard.core;

import com.matsnowak.piguard.model.SlotAddress;

/**
 * Created by Mateusz Nowak on 18.08.2016.
 */
public interface SlotAddressMapping<T> {

    T map(SlotAddress slotAddress);

    SlotAddress map(T input);
}
