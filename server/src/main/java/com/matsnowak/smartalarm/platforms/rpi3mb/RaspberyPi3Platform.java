package com.matsnowak.smartalarm.platforms.rpi3mb;

import com.google.common.collect.Lists;
import com.matsnowak.smartalarm.core.EventService;
import com.matsnowak.smartalarm.core.Events;
import com.matsnowak.smartalarm.core.Platform;
import com.matsnowak.smartalarm.core.SlotAddressMapping;
import com.matsnowak.smartalarm.core.events.SensorActivatedEvent;
import com.matsnowak.smartalarm.model.*;
import com.matsnowak.smartalarm.repositories.SensorRepository;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    @Autowired
    private EventService eventService;

    private GpioController gpioController;


    @Override
    public void update(Slot slot) {
//        SlotState state = slot.getState();
//        if (SlotState.INPUT.equals(state)) {
//            getController().provisionDigitalInputPin(mapSlot(slot), PinPullResistance.PULL_DOWN);
//        }
//        if (SlotState.OUTPUT.equals(state)) {
//            getController().provisionDigitalOutputPin(mapSlot(slot), PinState.HIGH);
//
//        }
//        if (SlotState.NOT_USED.equals(state)) {
//            getController().provisionDigitalOutputPin(mapSlot(slot), PinState.LOW); // TODO check for status
//        }
    }

    @Override
    public void startMonitoring(Zone zone) {
        zone.getSensors().forEach(sensor -> {
            configureSensor(sensor);
        });

        zone.getSignallers().forEach(signaller -> {
            configureSignaller(signaller);
        });

        logger.info("Started monitoring zone: " + zone);
    }

    private void configureSignaller(Signaller signaller) {
        getController().provisionDigitalOutputPin(mapSlot(signaller.getSlot()), PinState.LOW);
    }

    @Override
    public void stopMonitoring(Zone zone) {
        zone.getSensors().forEach( sensor -> {
            GpioPin input = getController().getProvisionedPin(mapSlot(sensor.getSlot()));
            input.removeAllListeners();
            logger.debug("Stopped monitoring input " + sensor.getSlot());
        });

        zone.getSignallers().forEach(signaller -> {
            getController().provisionDigitalOutputPin(mapSlot(signaller.getSlot()), PinState.LOW);
        });

//        gpioController.shutdown(); // TODO add application shutdown
    }

    @Override
    public void enableSignallers(Set<Signaller> signallers) {
        signallers.forEach(signaller -> {
            GpioPinDigitalOutput gpioPinDigitalOutput = getController().provisionDigitalOutputPin(mapSlot(signaller.getSlot()), PinState.HIGH);
            logger.debug("Enable signaller " + signaller);
        });
    }


    private GpioController getController() {
        if (gpioController == null) {
            gpioController = GpioFactory.getInstance();
        }

        return gpioController;
    }

    private void configureSensor(Sensor sensor) {
        GpioPinDigitalInput gpioPinDigitalInput = getController().provisionDigitalInputPin(mapSlot(sensor.getSlot()), PinPullResistance.PULL_DOWN);
        gpioPinDigitalInput.addListener(new InputPinListener());
        logger.debug("Configured sensor " + sensor + " as input");
    }

    private void publishEvent(SensorActivatedEvent sensorActivatedEvent) {
        eventService.publish(sensorActivatedEvent);
    }

    private Pin mapSlot(Slot slot) {
        Objects.requireNonNull(slot);
        Objects.requireNonNull(slot.getAddress());

        return slotAddressMapping.map(slot.getAddress());
    }

    public void setSlotAddressMapping(SlotAddressMapping<Pin> slotAddressMapping) {
        this.slotAddressMapping = slotAddressMapping;
    }


    private class InputPinListener implements GpioPinListenerDigital {

        @Override
        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
            if (event.getState() == PinState.HIGH) {
                SlotAddress slotAddress = slotAddressMapping.map(event.getPin().getPin());
                Sensor sensorByAddress = sensorRepository.findBySlotAddress(slotAddress);
                publishEvent(Events.newSlotActivatedEvent(sensorByAddress.getId()));
            }
        }
    }
}
