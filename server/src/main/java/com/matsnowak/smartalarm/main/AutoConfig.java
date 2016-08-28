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
        result.add(new Slot(SlotAddress.SLOT_1,     SlotState.NOT_USED, "Raspberry3 Pin 11"));
        result.add(new Slot(SlotAddress.SLOT_2,     SlotState.NOT_USED, "Raspberry3 Pin 12"));
        result.add(new Slot(SlotAddress.SLOT_3,     SlotState.NOT_USED, "Raspberry3 Pin 13"));
        result.add(new Slot(SlotAddress.SLOT_4,     SlotState.NOT_USED, "Raspberry3 Pin 15"));
        result.add(new Slot(SlotAddress.SLOT_5,     SlotState.NOT_USED, "Raspberry3 Pin 16"));
        result.add(new Slot(SlotAddress.SLOT_6,     SlotState.NOT_USED, "Raspberry3 Pin 18"));
        result.add(new Slot(SlotAddress.SLOT_7,     SlotState.NOT_USED, "Raspberry3 Pin 22"));
        result.add(new Slot(SlotAddress.SLOT_8,     SlotState.NOT_USED, "Raspberry3 Pin 7"));
        result.add(new Slot(SlotAddress.SLOT_9,     SlotState.NOT_USED, "Raspberry3 Pin 3"));
        result.add(new Slot(SlotAddress.SLOT_10,    SlotState.NOT_USED, "Raspberry3 Pin 5"));
        result.add(new Slot(SlotAddress.SLOT_11,    SlotState.NOT_USED, "Raspberry3 Pin 24"));
        result.add(new Slot(SlotAddress.SLOT_12,    SlotState.NOT_USED, "Raspberry3 Pin 26"));
        result.add(new Slot(SlotAddress.SLOT_13,    SlotState.NOT_USED, "Raspberry3 Pin 19"));
        result.add(new Slot(SlotAddress.SLOT_14,    SlotState.NOT_USED, "Raspberry3 Pin 21"));
        result.add(new Slot(SlotAddress.SLOT_15,    SlotState.NOT_USED, "Raspberry3 Pin 23"));
        result.add(new Slot(SlotAddress.SLOT_16,    SlotState.NOT_USED, "Raspberry3 Pin 8"));
        result.add(new Slot(SlotAddress.SLOT_17,    SlotState.NOT_USED, "Raspberry3 Pin 10"));
        result.add(new Slot(SlotAddress.SLOT_18,    SlotState.NOT_USED, "Raspberry3 Pin 29"));
        result.add(new Slot(SlotAddress.SLOT_19,    SlotState.NOT_USED, "Raspberry3 Pin 31"));
        result.add(new Slot(SlotAddress.SLOT_20,    SlotState.NOT_USED, "Raspberry3 Pin 33"));
        result.add(new Slot(SlotAddress.SLOT_21,    SlotState.NOT_USED, "Raspberry3 Pin 35"));
        result.add(new Slot(SlotAddress.SLOT_22,    SlotState.NOT_USED, "Raspberry3 Pin 37"));
        result.add(new Slot(SlotAddress.SLOT_23,    SlotState.NOT_USED, "Raspberry3 Pin 32"));
        result.add(new Slot(SlotAddress.SLOT_24,    SlotState.NOT_USED, "Raspberry3 Pin 36"));
        result.add(new Slot(SlotAddress.SLOT_25,    SlotState.NOT_USED, "Raspberry3 Pin 38"));
        result.add(new Slot(SlotAddress.SLOT_26,    SlotState.NOT_USED, "Raspberry3 Pin 40"));

        return result;
    }
}
