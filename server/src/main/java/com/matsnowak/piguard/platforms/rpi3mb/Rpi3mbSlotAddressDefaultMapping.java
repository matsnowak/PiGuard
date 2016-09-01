package com.matsnowak.piguard.platforms.rpi3mb;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import com.matsnowak.piguard.core.SlotAddressMapping;
import com.matsnowak.piguard.model.SlotAddress;
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
        map.put(SlotAddress.SLOT_8, RaspiPin.GPIO_07);
        map.put(SlotAddress.SLOT_9, RaspiPin.GPIO_08);
        map.put(SlotAddress.SLOT_10,RaspiPin.GPIO_09);
        map.put(SlotAddress.SLOT_11, RaspiPin.GPIO_10);
        map.put(SlotAddress.SLOT_12, RaspiPin.GPIO_11);
        map.put(SlotAddress.SLOT_13, RaspiPin.GPIO_12);
        map.put(SlotAddress.SLOT_14, RaspiPin.GPIO_13);
        map.put(SlotAddress.SLOT_15, RaspiPin.GPIO_14);
        map.put(SlotAddress.SLOT_16, RaspiPin.GPIO_15);
        map.put(SlotAddress.SLOT_17, RaspiPin.GPIO_16);
        map.put(SlotAddress.SLOT_18, RaspiPin.GPIO_21);
        map.put(SlotAddress.SLOT_19, RaspiPin.GPIO_22);
        map.put(SlotAddress.SLOT_20, RaspiPin.GPIO_23);
        map.put(SlotAddress.SLOT_21, RaspiPin.GPIO_24);
        map.put(SlotAddress.SLOT_22, RaspiPin.GPIO_25);
        map.put(SlotAddress.SLOT_23, RaspiPin.GPIO_26);
        map.put(SlotAddress.SLOT_24, RaspiPin.GPIO_27);
        map.put(SlotAddress.SLOT_25, RaspiPin.GPIO_28);
        map.put(SlotAddress.SLOT_26, RaspiPin.GPIO_29);
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
