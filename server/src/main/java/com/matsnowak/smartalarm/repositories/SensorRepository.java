package com.matsnowak.smartalarm.repositories;

import com.matsnowak.smartalarm.model.Sensor;
import com.matsnowak.smartalarm.model.SlotAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import static com.matsnowak.smartalarm.main.ApiUrls.API_SENSORS;

/**
 * Created by Mateusz on 20.06.2016.
 */
@RepositoryRestResource(collectionResourceRel = API_SENSORS, path = API_SENSORS)
public interface SensorRepository extends CrudRepository<Sensor, Integer> {
    Sensor findBySlotAddress(SlotAddress slotAddress);

}
