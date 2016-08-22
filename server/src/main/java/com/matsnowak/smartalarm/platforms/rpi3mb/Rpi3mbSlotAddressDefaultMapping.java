package com.matsnowak.smartalarm.platforms.rpi3mb;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import com.matsnowak.smartalarm.core.SlotAddressMapping;
import com.matsnowak.smartalarm.model.SlotAddress;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Nowak on 18.08.2016.
 */

@Component
@Qualifier("defaultMapping")
public class Rpi3mbSlotAddressDefaultMapping implements SlotAddressMapping<Pin> {
    private static final ImmutableBiMap<SlotAddress, Pin> slotMapping;

    static {
        BiMap<SlotAddress, Pin> map = HashBiMap.create();
        map.put(SlotAddress.SLOT_1, RaspiPin.GPIO_00);
        map.put(SlotAddress.SLOT_2, RaspiPin.GPIO_01);
        map.put(SlotAddress.SLOT_3, RaspiPin.GPIO_02);
        map.put(SlotAddress.SLOT_4, RaspiPin.GPIO_03);
        map.put(SlotAddress.SLOT_5, RaspiPin.GPIO_04);
        map.put(SlotAddress.SLOT_6, RaspiPin.GPIO_05);
        map.put(SlotAddress.SLOT_7, RaspiPin.GPIO_06);
        // TODO finish

        slotMapping = ImmutableBiMap.copyOf(map);
    }

    @Override
    public Pin map(SlotAddress slotAddress) {
        return slotMapping.get(slotAddress);
    }

    @Override
    public SlotAddress map(Pin input) {
        return slotMapping.inverse().get(input);
    }
}
