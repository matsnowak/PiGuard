package com.matsnowak.smartalarm.main.services;

import com.matsnowak.smartalarm.main.repositories.CommunicationSlotRepository;
import com.matsnowak.smartalarm.model.CommunicationSlot;
import com.matsnowak.smartalarm.model.CommunicationSlotAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Mateusz on 19.06.2016.
 */
@Service
public class CommunicationSlotService {

    @Autowired
    CommunicationSlotRepository slotRepository;

    public List<CommunicationSlot> getAllSlots() {
        return slotRepository.findAll();
    }

    public Optional<CommunicationSlot> getSlotById(Integer id) {
        return slotRepository.findById(id);
    }

    public CommunicationSlot update(CommunicationSlot slotById) {
        return slotRepository.save(slotById);
    }
}
