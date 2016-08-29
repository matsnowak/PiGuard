package com.matsnowak.smartalarm.repositories;

import com.matsnowak.smartalarm.model.Slot;
import com.matsnowak.smartalarm.model.SlotAddress;
import com.matsnowak.smartalarm.model.SlotMode;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Mateusz Nowak on 18.08.2016.
 */
public class SlotRepositoryTest extends AbstractRepositoryTest{

    @Autowired
    SlotRepository slotRepository;


    @Ignore // TODO fix later
    @Test
    public void shouldFindByAddress() throws Exception {
        // given
        Slot s = new Slot(SlotAddress.SLOT_11, SlotMode.INPUT);
        Slot saved = slotRepository.save(s);

        // when
        Slot byAddress = slotRepository.findByAddress(saved.getAddress());

        //then
        assertThat(byAddress).isEqualTo(saved);
    }
}