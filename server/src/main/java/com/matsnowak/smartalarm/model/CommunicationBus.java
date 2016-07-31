package com.matsnowak.smartalarm.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mateusz on 19.06.2016.
 */
public class CommunicationBus {
    private List<Slot> slots = new LinkedList<>();

    public List<Slot> getAllSLots() {
        return Collections.unmodifiableList(slots);
    }

    public List<Slot> getSlotsByState(SlotState state) {
        return slots.stream()
                .filter(slot -> slot.getState() == state)
                .collect(Collectors.toList());
    }


}
