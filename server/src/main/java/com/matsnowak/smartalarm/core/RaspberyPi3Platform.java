package com.matsnowak.smartalarm.core;

import com.google.common.collect.*;
import com.matsnowak.smartalarm.model.Slot;
import com.matsnowak.smartalarm.model.SlotAddress;
import com.matsnowak.smartalarm.model.SlotState;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.wiringpi.Gpio;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Mateusz Nowak on 17.08.2016.
 */
public class RaspberyPi3Platform {

    private static final ImmutableBiMap<SlotAddress, Pin> slotAddressToPinMap;
    private final List<GpioPinDigitalInput> inputs = Lists.newLinkedList();


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

        slotAddressToPinMap = ImmutableBiMap.copyOf(map);
    }


    GpioController gpioController;

    public void startMonitoring(List<Slot> slotList) {
        gpioController = GpioFactory.getInstance();

        slotList.forEach(slot -> {
            if (slot.getState() == SlotState.INPUT) {
                GpioPinDigitalInput gpioPinDigitalInput = gpioController.provisionDigitalInputPin(mapSlot(slot), PinPullResistance.PULL_DOWN);
                inputs.add(gpioPinDigitalInput);
                gpioPinDigitalInput.addListener(new GpioPinListenerDigital() {
                    @Override
                    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                        if (event.getState() == PinState.HIGH) {
                            // TODO generate sensorActivatedEvent
                        }

                    }
                });
            }
        });

    }

    private Pin mapSlot(Slot slot) {
        Objects.requireNonNull(slot);
        Objects.requireNonNull(slot.getAddress());

        return slotAddressToPinMap.get(slot.getAddress());
    }

    public void shutdown() {
        inputs.forEach(input -> {
            input.removeAllListeners();
            input.removeAllTriggers();
        });


        gpioController.shutdown();
    }

}
