package com.matsnowak.smartalarm.main.repositories;

import com.matsnowak.smartalarm.model.Zone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Mateusz on 30.06.2016.
 */
public interface ZoneRepository extends CrudRepository<Zone, Integer> {
    List<Zone> findAll();

    Optional<Zone> findById(Integer id);
}
