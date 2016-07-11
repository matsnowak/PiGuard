package com.matsnowak.smartalarm.main.services;

import com.matsnowak.smartalarm.main.exceptions.SensorException;
import com.matsnowak.smartalarm.main.repositories.SensorRepository;
import com.matsnowak.smartalarm.model.CommunicationSlot;
import com.matsnowak.smartalarm.model.CommunicationSlotState;
import com.matsnowak.smartalarm.model.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by Mateusz on 20.06.2016.
 */

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private CommunicationSlotService slotService;


    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    public Optional<Sensor> find(Integer id) {
        return sensorRepository.findById(id);
    }

    public Sensor add(Sensor newSensor) throws SensorException {
        CommunicationSlot communicationSlot = newSensor.getCommunicationSlot();
        CommunicationSlot slotById = slotService.getSlotById(communicationSlot.getId()).get();
        if (slotById.getState() != CommunicationSlotState.NOT_USED) {
            throw new SensorException("Communication slot with id: " + slotById.getId() + " is already used:");
        }

        slotById.setState(CommunicationSlotState.INPUT);
        CommunicationSlot updatedCommunicationSlot = slotService.update(slotById);

        newSensor.setCommunicationSlot(updatedCommunicationSlot);

        return sensorRepository.save(newSensor);
    }


    public void delete(Sensor sensor) {
        sensorRepository.delete(sensor);
    }
}
