package com.matsnowak.smartalarm.main;

/**
 * Created by Mateusz on 20.06.2016.
 */

import com.matsnowak.smartalarm.model.Slot;
import com.matsnowak.smartalarm.model.SlotAddress;
import com.matsnowak.smartalarm.model.SlotState;
import com.matsnowak.smartalarm.repositories.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Temporary used for loading settings
 */

@Component()
public class AutoConfig {

    @Autowired
    SlotRepository slotRepository;


    public void init() {
        slotRepository.save(slots());
    }

    public List<Slot> slots() {
        List<Slot> result = new LinkedList<>();
        result.add(new Slot(SlotAddress.SLOT_1, SlotState.NOT_USED));
        result.add(new Slot(SlotAddress.SLOT_2, SlotState.NOT_USED));
        result.add(new Slot(SlotAddress.SLOT_3, SlotState.NOT_USED));
        result.add(new Slot(SlotAddress.SLOT_4, SlotState.NOT_USED));
        result.add(new Slot(SlotAddress.SLOT_5, SlotState.NOT_USED));
        result.add(new Slot(SlotAddress.SLOT_6, SlotState.NOT_USED));
        result.add(new Slot(SlotAddress.SLOT_7, SlotState.NOT_USED));
        result.add(new Slot(SlotAddress.SLOT_8, SlotState.NOT_USED));
        result.add(new Slot(SlotAddress.SLOT_9, SlotState.NOT_USED));
        result.add(new Slot(SlotAddress.SLOT_10, SlotState.NOT_USED));

        return result;
    }
}
