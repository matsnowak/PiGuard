package com.matsnowak.piguard.repositories;

import com.matsnowak.piguard.model.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


/**
 * Created by Mateusz Nowak on 07.09.2016.
 */
public class SlotRepositoryTest  extends AbstractRepositoryTest{

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    SignallerRepository signallerRepository;

    @Autowired
    SlotRepository slotRepository;
    @Test
    public void shouldFindFreeSlots() throws Exception {
        // given
        Set<Slot> all = slotRepository.findAll();

        // when
        Set<Slot> freeSlots = slotRepository.free();

        //then
        assertThat(freeSlots).isEqualTo(all);
    }

    @Test
    public void shouldFindFreeSlots2() throws Exception {
        // given
        Slot one = slotRepository.findByAddress(SlotAddress.SLOT_1);
        Slot two = slotRepository.findByAddress(SlotAddress.SLOT_2);
        signallerRepository.save(new Signaller("name", one));
        sensorRepository.save(new Sensor("name2", two, TriggeringType.EDGE, PullResistance.PULL_DOWN));

        // when
        Set<Slot> freeSlots = slotRepository.free();

        //then
        assertThat(freeSlots).hasSize(slotRepository.findAll().size() - 2);
        assertThat(freeSlots).doesNotContain(one, two);
    }

}