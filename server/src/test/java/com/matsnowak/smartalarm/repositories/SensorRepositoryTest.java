package com.matsnowak.smartalarm.repositories;

import com.matsnowak.smartalarm.model.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by Mateusz Nowak on 18.08.2016.
 */


public class SensorRepositoryTest extends AbstractRepositoryTest{

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    SlotRepository slotRepository;

    @Test
    public void shouldFindBySlotAddress() throws Exception {
        // given
        Sensor s = new Sensor("name", slotRepository.findByAddress(SlotAddress.SLOT_9), TriggeringType.EDGE, PullResistance.OFF);
        Sensor saved = sensorRepository.save(s);

        // when
        Sensor bySlotAddress = sensorRepository.findBySlotAddress(saved.getSlot().getAddress());

        // then
        assertThat(bySlotAddress).isEqualTo(saved);

    }
}