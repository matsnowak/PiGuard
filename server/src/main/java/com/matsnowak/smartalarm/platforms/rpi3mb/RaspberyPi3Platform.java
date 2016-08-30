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
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;

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
        GpioPin provisionedPin = getController().getProvisionedPin(mapSlot(signaller.getSlot()));
        if (provisionedPin != null) {
            getController().unprovisionPin(provisionedPin);
            logger.debug("Unprovisioned pin " + provisionedPin);
        }

        GpioPinDigitalOutput gpioPinDigitalOutput = getController().provisionDigitalOutputPin(mapSlot(signaller.getSlot()), PinState.LOW);
        if (gpioPinDigitalOutput != null) {
            gpioPinDigitalOutput.setShutdownOptions(true, PinState.LOW);
            logger.debug("Configured signaller " + signaller);
        }
    }

    @Override
    public void stopMonitoring(Zone zone) {
        logger.debug("Stop monitoring zone: " + zone);
        zone.getSensors().forEach( sensor -> {
            GpioPin sensorPin = getController().getProvisionedPin(mapSlot(sensor.getSlot()));
            if (sensorPin != null) {
                sensorPin.removeAllListeners();
                getController().unprovisionPin(sensorPin);
                logger.debug("Stopped monitoring sensor " + sensor);
            }
        });

        zone.getSignallers().forEach(signaller -> {
            GpioPin signallerPin = getController().getProvisionedPin(mapSlot(signaller.getSlot()));
            if (signallerPin != null) {
                getController().unprovisionPin(signallerPin);
                logger.debug("Stopped signaller " + signaller);
            }
            GpioPinDigitalOutput gpioPinDigitalOutput = getController().provisionDigitalOutputPin(mapSlot(signaller.getSlot()), PinState.LOW);
            if (gpioPinDigitalOutput != null) {
                gpioPinDigitalOutput.setShutdownOptions(true, PinState.LOW);
                logger.debug("Configured signaller to low state " + signaller);
            }
        });

    }

    @Override
    public void enableSignallers(Set<Signaller> signallers) {
        signallers.forEach(signaller -> {
            GpioPin provisionedPin = getController().getProvisionedPin(mapSlot(signaller.getSlot()));
            if (provisionedPin != null) {
                GpioPinDigitalOutput digitalOutput = (GpioPinDigitalOutput) provisionedPin;
                if (digitalOutput.isLow()) {
                    digitalOutput.high();
                }
                logger.debug("Set pin to High" + provisionedPin);
            } else {
                GpioPinDigitalOutput gpioPinDigitalOutput = getController().provisionDigitalOutputPin(mapSlot(signaller.getSlot()), PinState.HIGH);
                gpioPinDigitalOutput.setShutdownOptions(true, PinState.LOW);
                logger.debug("Enable signaller " + signaller);
            }
        });
    }


    private void configureSensor(Sensor sensor) {

        GpioPin provisionedSensorPin = getController().getProvisionedPin(mapSlot(sensor.getSlot()));
        if (provisionedSensorPin != null) {
            getController().unprovisionPin(provisionedSensorPin);
            logger.debug("Unprovisioned pin " + provisionedSensorPin);
        }

        GpioPinDigitalInput gpioPinDigitalInput = getController().provisionDigitalInputPin(
                mapSlot(sensor.getSlot()),
                mapPullResistance(sensor.getPullResistance()));

        if (gpioPinDigitalInput != null) {
            listenerFor(sensor.getTriggeredOn()).ifPresent(listener -> gpioPinDigitalInput.addListener(listener));
            gpioPinDigitalInput.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
            logger.debug("Configured sensor " + sensor);
        }
    }

    private Optional<GpioPinListenerDigital> listenerFor(TriggeringType triggeredOn) {
        if (TriggeringType.EDGE.equals(triggeredOn)) {
            return Optional.of(new TriggerOnEdgePinListener());
        }

        if (TriggeringType.LOW_STATE.equals(triggeredOn)) {
            return Optional.of(new TriggerOnLowStatePinListener());
        }

        if (TriggeringType.HIGH_STATE.equals(triggeredOn)) {
            return Optional.of(new TriggerOnHighStatePinListener());
        }

        return Optional.empty();
    }

    private GpioController getController() {
        if (gpioController == null) {
            gpioController = GpioFactory.getInstance();
        }

        return gpioController;
    }

    private void publishEvent(SensorActivatedEvent sensorActivatedEvent) {
        eventService.publish(sensorActivatedEvent);
    }

    private Pin mapSlot(Slot slot) {
        Objects.requireNonNull(slot);
        Objects.requireNonNull(slot.getAddress());

        return slotAddressMapping.map(slot.getAddress());
    }

    private PinPullResistance mapPullResistance(PullResistance pullResistance) {
        if (pullResistance == null) {
            return PinPullResistance.OFF;
        }

        if (PullResistance.PULL_DOWN.equals(pullResistance)) {
            return PinPullResistance.PULL_DOWN;
        }

        if (PullResistance.PULL_UP.equals(pullResistance)) {
            return PinPullResistance.PULL_UP;
        }

        return PinPullResistance.OFF;
    }

    public void setSlotAddressMapping(SlotAddressMapping<Pin> slotAddressMapping) {
        this.slotAddressMapping = slotAddressMapping;
    }


    private class TriggerOnEdgePinListener implements GpioPinListenerDigital {
        @Override
        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
            SlotAddress slotAddress = slotAddressMapping.map(event.getPin().getPin());
            Sensor sensorByAddress = sensorRepository.findBySlotAddress(slotAddress);
            publishEvent(Events.newSlotActivatedEvent(sensorByAddress.getId()));
        }
    }

    private class TriggerOnLowStatePinListener implements GpioPinListenerDigital {
        @Override
        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
            if (event.getState() == PinState.LOW) {
                SlotAddress slotAddress = slotAddressMapping.map(event.getPin().getPin());
                Sensor sensorByAddress = sensorRepository.findBySlotAddress(slotAddress);
                publishEvent(Events.newSlotActivatedEvent(sensorByAddress.getId()));
            }
        }
    }

    private class TriggerOnHighStatePinListener implements GpioPinListenerDigital {
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
