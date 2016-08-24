package com.matsnowak.smartalarm.repositories;

import com.matsnowak.smartalarm.model.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

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

    @Test
    public void shouldSaveZoneWithRelations() throws Exception {
        // given
//        Slot savedSlot1 = slots.save(new Slot(SlotAddress.SLOT_1, SlotState.INPUT));
//        Slot savedSlot2 = slots.save(new Slot(SlotAddress.SLOT_2, SlotState.OUTPUT));

//        Sensor savedS1 = sensors.save(new Sensor("s1", SensorType.PIR, savedSlot1));
//        Sensor savedS2 = sensors.save(new Sensor("s2", SensorType.CONTACTRON, savedSlot2));

        Sensor savedS1 = sensors.save(new Sensor("s1", SensorType.PIR, slots.findOne(1)));
        Sensor savedS2 = sensors.save(new Sensor("s2", SensorType.CONTACTRON, slots.findOne(2)));


        // when
        Zone savedZone = zones.save(Zone.create("zone1", Arrays.asList(savedS1, savedS2)));
        Zone fromRepo = zones.findOne(savedZone.getId());

        //then
        assertThat(fromRepo.getSensors()).hasSize(2);


    }
}