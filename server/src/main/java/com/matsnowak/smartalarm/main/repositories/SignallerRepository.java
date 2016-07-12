package com.matsnowak.smartalarm.main.repositories;

import com.matsnowak.smartalarm.model.Signaller;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Mateusz on 24.06.2016.
 */
public interface SignallerRepository extends CrudRepository<Signaller, Integer> {
    List<Signaller> findAll();

    Optional<Signaller> findById(Integer id);
}
