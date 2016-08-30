package com.matsnowak.smartalarm.repositories;

import com.google.common.collect.Sets;
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
        Sensor savedS1 = sensors.save(new Sensor("s1",  slots.findOne(1), TriggeringType.EDGE, PullResistance.OFF));
        Sensor savedS2 = sensors.save(new Sensor("s2",  slots.findOne(2), TriggeringType.EDGE, PullResistance.OFF));
        Sensor savedS3 = sensors.save(new Sensor("s2",  slots.findOne(3), TriggeringType.EDGE, PullResistance.OFF));

        Signaller sig1 = signallers.save(new Signaller("sig1", slots.findOne(4)));
        Signaller sig2 = signallers.save(new Signaller("sig2", slots.findOne(5)));
        Signaller sig3 = signallers.save(new Signaller("sig3", slots.findOne(6)));




        // when

        Iterable<Zone> zonesAll = zones.findAll();
        Zone savedZone = zones.save(Zone.create(
                "zone1",
                Sets.newHashSet(savedS1, savedS2, savedS3),
                Sets.newHashSet(sig1, sig2, sig3))
        );

        Zone fromRepo = zones.findOne(savedZone.getId());

        //then
        assertThat(fromRepo.getSensors()).hasSize(3);
        assertThat(fromRepo.getSignallers()).hasSize(3);
    }
}