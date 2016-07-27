package com.matsnowak.smartalarm.repositories;

import com.matsnowak.smartalarm.model.CommunicationSlot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by Mateusz on 26.07.2016.
 */

@RepositoryRestResource(collectionResourceRel = "slots", path = "slots")
public interface  CommunicationSlotRepository extends CrudRepository<CommunicationSlot, Integer> {
   @RestResource(exported = false)
    void delete(Integer id);

    @RestResource(exported = false)
    void delete(CommunicationSlot entity);


    @RestResource(exported = false)
    void deleteAll();
}
