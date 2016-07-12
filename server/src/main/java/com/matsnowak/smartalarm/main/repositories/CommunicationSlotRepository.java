package com.matsnowak.smartalarm.main.repositories;

import com.matsnowak.smartalarm.model.CommunicationSlot;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Mateusz on 20.06.2016.
 */
public interface CommunicationSlotRepository extends CrudRepository<CommunicationSlot, Integer> {
    List<CommunicationSlot> findAll();

    Optional<CommunicationSlot> findById(Integer id);
}
