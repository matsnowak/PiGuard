package com.matsnowak.smartalarm.repositories;

import com.matsnowak.smartalarm.model.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by Mateusz Nowak on 25.08.2016.
 */
public class ZoneRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    ZoneRepository zones;

    @Autowired
    SlotRepository slots;

    @Autowired
    SensorRepository sensors;

    @Autowired
    SignallerRepository signallers;

    @Test
    public void shouldSaveZoneWithRelations() throws Exception {
        //given
        Sensor savedS1 = sensors.save(new Sensor("s1", SensorType.PIR, slots.findOne(1)));
        Sensor savedS2 = sensors.save(new Sensor("s2", SensorType.CONTACTRON, slots.findOne(2)));
        Sensor savedS3 = sensors.save(new Sensor("s2", SensorType.CONTACTRON, slots.findOne(3)));

        Signaller sig1 = signallers.save(new Signaller("sig1", slots.findOne(4)));
        Signaller sig2 = signallers.save(new Signaller("sig2", slots.findOne(5)));
        Signaller sig3 = signallers.save(new Signaller("sig3", slots.findOne(6)));




        // when

        Iterable<Zone> zonesAll = zones.findAll();
        Zone savedZone = zones.save(Zone.create(
                "zone1",
                Arrays.asList(savedS1, savedS2, savedS3),
                Arrays.asList(sig1, sig2, sig3))
        );

        Zone fromRepo = zones.findOne(savedZone.getId());

        //then
        assertThat(fromRepo.getSensors()).hasSize(3);
        assertThat(fromRepo.getSignallers()).hasSize(3);
    }
}