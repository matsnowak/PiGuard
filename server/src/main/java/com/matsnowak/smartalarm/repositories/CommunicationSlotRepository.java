package com.matsnowak.smartalarm.repositories;

import com.matsnowak.smartalarm.main.ApiUrls;
import com.matsnowak.smartalarm.model.CommunicationSlot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by Mateusz Nowak on 26.07.2016.
 */

@RepositoryRestResource(collectionResourceRel = ApiUrls.API_SLOTS, path = ApiUrls.API_SLOTS)
public interface  CommunicationSlotRepository extends CrudRepository<CommunicationSlot, Integer> {
   @RestResource(exported = false)
    void delete(Integer id);

    @RestResource(exported = false)
    void delete(CommunicationSlot entity);


    @RestResource(exported = false)
    void deleteAll();
}
