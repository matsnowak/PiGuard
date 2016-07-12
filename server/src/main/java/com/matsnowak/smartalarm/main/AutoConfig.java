package com.matsnowak.smartalarm.main;

/**
 * Created by Mateusz on 20.06.2016.
 */

import com.matsnowak.smartalarm.main.repositories.CommunicationSlotRepository;
import com.matsnowak.smartalarm.model.CommunicationSlot;
import com.matsnowak.smartalarm.model.CommunicationSlotAddress;
import com.matsnowak.smartalarm.model.CommunicationSlotState;
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
    CommunicationSlotRepository slotRepository;


    public void init() {
        slotRepository.save(slots());
    }

    private List<CommunicationSlot> slots() {
        List<CommunicationSlot> result = new LinkedList<>();
        result.add(new CommunicationSlot(CommunicationSlotAddress.SLOT_1, CommunicationSlotState.NOT_USED));
        result.add(new CommunicationSlot(CommunicationSlotAddress.SLOT_2, CommunicationSlotState.NOT_USED));
        result.add(new CommunicationSlot(CommunicationSlotAddress.SLOT_3, CommunicationSlotState.NOT_USED));
        result.add(new CommunicationSlot(CommunicationSlotAddress.SLOT_4, CommunicationSlotState.NOT_USED));
        result.add(new CommunicationSlot(CommunicationSlotAddress.SLOT_5, CommunicationSlotState.NOT_USED));
        result.add(new CommunicationSlot(CommunicationSlotAddress.SLOT_6, CommunicationSlotState.NOT_USED));
        result.add(new CommunicationSlot(CommunicationSlotAddress.SLOT_7, CommunicationSlotState.NOT_USED));
        result.add(new CommunicationSlot(CommunicationSlotAddress.SLOT_8, CommunicationSlotState.NOT_USED));
        result.add(new CommunicationSlot(CommunicationSlotAddress.SLOT_9, CommunicationSlotState.NOT_USED));
        result.add(new CommunicationSlot(CommunicationSlotAddress.SLOT_10, CommunicationSlotState.NOT_USED));

        return result;
    }
}
