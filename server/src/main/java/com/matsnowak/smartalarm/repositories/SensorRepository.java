package com.matsnowak.smartalarm.repositories;

import com.matsnowak.smartalarm.model.Sensor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

/**
 * Created by Mateusz on 20.06.2016.
 */
@RepositoryRestResource(collectionResourceRel = "sensors", path = "sensors")
public interface SensorRepository extends CrudRepository<Sensor, Integer> {

}
