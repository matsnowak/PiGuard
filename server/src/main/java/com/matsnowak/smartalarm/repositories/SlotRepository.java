package com.matsnowak.smartalarm.repositories;

import com.matsnowak.smartalarm.main.ApiUrls;
import com.matsnowak.smartalarm.model.Slot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by Mateusz Nowak on 26.07.2016.
 */

@RepositoryRestResource(collectionResourceRel = ApiUrls.API_SLOTS, path = ApiUrls.API_SLOTS)
public interface SlotRepository extends CrudRepository<Slot, Integer> {
   @RestResource(exported = false)
    void delete(Integer id);

    @RestResource(exported = false)
    void delete(Slot entity);


    @RestResource(exported = false)
    void deleteAll();
}
