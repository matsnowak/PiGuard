package com.matsnowak.piguard.main;

/**
 * Created by Mateusz on 20.06.2016.
 */

import com.matsnowak.piguard.model.Settings;
import com.matsnowak.piguard.model.Slot;
import com.matsnowak.piguard.model.SlotAddress;
import com.matsnowak.piguard.repositories.SettingsRepository;
import com.matsnowak.piguard.repositories.SlotRepository;
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

    @Autowired
    SettingsRepository settingsRepository;


    public void init() {
        slotRepository.save(slots());
        initDefaultSettings();
    }

    private void initDefaultSettings() {
        Settings one = settingsRepository.findOne(1);
        if (one == null) {
            one = new Settings();
            one.setDisarmDelay(30);
            one.setExitDelay(45);
            settingsRepository.save(one);
        }
    }

    public List<Slot> slots() {
        List<Slot> result = new LinkedList<>();
        result.add(new Slot(SlotAddress.SLOT_1,     "Raspberry3 Pin 11"));
        result.add(new Slot(SlotAddress.SLOT_2,     "Raspberry3 Pin 12"));
        result.add(new Slot(SlotAddress.SLOT_3,     "Raspberry3 Pin 13"));
        result.add(new Slot(SlotAddress.SLOT_4,     "Raspberry3 Pin 15"));
        result.add(new Slot(SlotAddress.SLOT_5,     "Raspberry3 Pin 16"));
        result.add(new Slot(SlotAddress.SLOT_6,     "Raspberry3 Pin 18"));
        result.add(new Slot(SlotAddress.SLOT_7,     "Raspberry3 Pin 22"));
        result.add(new Slot(SlotAddress.SLOT_8,     "Raspberry3 Pin 7"));
        result.add(new Slot(SlotAddress.SLOT_9,     "Raspberry3 Pin 3"));
        result.add(new Slot(SlotAddress.SLOT_10,    "Raspberry3 Pin 5"));
        result.add(new Slot(SlotAddress.SLOT_11,    "Raspberry3 Pin 24"));
        result.add(new Slot(SlotAddress.SLOT_12,    "Raspberry3 Pin 26"));
        result.add(new Slot(SlotAddress.SLOT_13,    "Raspberry3 Pin 19"));
        result.add(new Slot(SlotAddress.SLOT_14,    "Raspberry3 Pin 21"));
        result.add(new Slot(SlotAddress.SLOT_15,    "Raspberry3 Pin 23"));
        result.add(new Slot(SlotAddress.SLOT_16,    "Raspberry3 Pin 8"));
        result.add(new Slot(SlotAddress.SLOT_17,    "Raspberry3 Pin 10"));
        result.add(new Slot(SlotAddress.SLOT_18,    "Raspberry3 Pin 29"));
        result.add(new Slot(SlotAddress.SLOT_19,    "Raspberry3 Pin 31"));
        result.add(new Slot(SlotAddress.SLOT_20,    "Raspberry3 Pin 33"));
        result.add(new Slot(SlotAddress.SLOT_21,    "Raspberry3 Pin 35"));
        result.add(new Slot(SlotAddress.SLOT_22,    "Raspberry3 Pin 37"));
        result.add(new Slot(SlotAddress.SLOT_23,    "Raspberry3 Pin 32"));
        result.add(new Slot(SlotAddress.SLOT_24,    "Raspberry3 Pin 36"));
        result.add(new Slot(SlotAddress.SLOT_25,    "Raspberry3 Pin 38"));
        result.add(new Slot(SlotAddress.SLOT_26,    "Raspberry3 Pin 40"));

        return result;
    }
}
