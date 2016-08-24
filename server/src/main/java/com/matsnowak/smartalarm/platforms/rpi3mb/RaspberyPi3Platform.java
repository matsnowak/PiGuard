package com.matsnowak.smartalarm.platforms.rpi3mb;

import com.google.common.collect.Lists;
import com.matsnowak.smartalarm.core.Events;
import com.matsnowak.smartalarm.core.Platform;
import com.matsnowak.smartalarm.core.SlotAddressMapping;
import com.matsnowak.smartalarm.core.events.SensorActivatedEvent;
import com.matsnowak.smartalarm.model.Sensor;
import com.matsnowak.smartalarm.model.Slot;
import com.matsnowak.smartalarm.model.SlotAddress;
import com.matsnowak.smartalarm.model.SlotState;
import com.matsnowak.smartalarm.repositories.SensorRepository;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * Created by Mateusz Nowak on 17.08.2016.
 */
public class RaspberyPi3Platform implements Platform {
    private final Logger logger = LoggerFactory.getLogger(RaspberyPi3Platform.class);

    private final List<GpioPinDigitalInput> inputs = Lists.newLinkedList();

    @Autowired
    @Qualifier("defaultMapping")
    private SlotAddressMapping<Pin> slotAddressMapping;

    @Autowired
    private SensorRepository sensorRepository;

    GpioController gpioController;

    @Override
    public void startMonitoring(List<Slot> slotList) {
        gpioController = GpioFactory.getInstance();

        slotList.forEach(slot -> {
            if (slot.getState() == SlotState.INPUT) {
                configureInputSlot(slot);
            }
        });
        logger.info("Start monitoring with slots" + slotList);
    }

    private void configureInputSlot(Slot slot) {
        GpioPinDigitalInput gpioPinDigitalInput = gpioController.provisionDigitalInputPin(mapSlot(slot), PinPullResistance.PULL_DOWN);
        inputs.add(gpioPinDigitalInput);
        gpioPinDigitalInput.addListener(new com.pi4j.io.gpio.event.GpioPinListener[]{new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                if (event.getState() == PinState.HIGH) {
                    SlotAddress slotAddress = slotAddressMapping.map(event.getPin().getPin());
                    Sensor sensorByAddress = sensorRepository.findBySlotAddress(slotAddress);
//                    publishEvent(Events.newSlotActivatedEvent(sensorByAddress)); // TODO back here and fix
                }
            }
        }});

    }

    private void publishEvent(SensorActivatedEvent sensorActivatedEvent) {
        System.out.println("Sensor activated: " + sensorActivatedEvent);
    }

    private Pin mapSlot(Slot slot) {
        Objects.requireNonNull(slot);
        Objects.requireNonNull(slot.getAddress());

        return slotAddressMapping.map(slot.getAddress());
    }

    @Override
    public void stopMonitoring() {
        inputs.forEach(input -> {
            input.removeAllListeners();
            input.removeAllTriggers();
        });

        gpioController.shutdown();
    }

    public void setSlotAddressMapping(SlotAddressMapping<Pin> slotAddressMapping) {
        this.slotAddressMapping = slotAddressMapping;
    }
}
