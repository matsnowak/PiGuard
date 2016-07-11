package com.matsnowak.smartalarm.main.services;

import com.matsnowak.smartalarm.main.repositories.SensorRepository;
import com.matsnowak.smartalarm.main.repositories.ZoneRepository;
import com.matsnowak.smartalarm.model.Sensor;
import com.matsnowak.smartalarm.model.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Mateusz on 30.06.2016.
 */
@Service
public class ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    @Qualifier("sensorRepository")
    @Autowired
    private SensorRepository sensorRepository;


    public List<Zone> findAll() {
        return zoneRepository.findAll();
    }

    public Optional<Zone> find(Integer id) {
        return zoneRepository.findById(id);
    }

    public Zone add(Zone newZone) {
        List<Sensor> sensors = newZone.getSensors();
        if (sensors != null) {
            List<Sensor> newSensors = sensors.stream()
                    .map(s -> sensorRepository.findById(s.getId()))
                    .filter(s -> s.isPresent())
                    .map(s -> s.get())
                    .collect(Collectors.toList());

            newZone.setSensors(newSensors);
        }

        return zoneRepository.save(newZone);
    }

    public void delete(Zone zone) {
        zoneRepository.delete(zone);
    }
}
