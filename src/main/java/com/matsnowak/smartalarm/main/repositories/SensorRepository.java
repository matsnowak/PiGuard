package com.matsnowak.smartalarm.main.repositories;

import com.matsnowak.smartalarm.model.Sensor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Mateusz on 20.06.2016.
 */
public interface SensorRepository extends CrudRepository<Sensor, Integer> {
    List<Sensor> findAll();

    Optional<Sensor> findById(Integer id);
}
