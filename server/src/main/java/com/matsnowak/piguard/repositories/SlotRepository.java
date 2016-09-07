package com.matsnowak.piguard.repositories;

import com.matsnowak.piguard.main.ApiUrls;
import com.matsnowak.piguard.model.Slot;
import com.matsnowak.piguard.model.SlotAddress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Set;

/**
 * Created by Mateusz Nowak on 26.07.2016.
 */

@RepositoryRestResource(collectionResourceRel = ApiUrls.API_SLOTS, path = ApiUrls.API_SLOTS)
public interface SlotRepository extends CrudRepository<Slot, Integer> {
    @RestResource(exported = false)
    @Override
    void delete(Integer id);

    @RestResource(exported = false)
    @Override
    void delete(Slot entity);

    @RestResource(exported = false)
    @Override
    Slot save(Slot entity);

    @RestResource(exported = false)
    @Override
    <S extends Slot> Iterable<S> save(Iterable<S> entities);

    Slot findByAddress(SlotAddress slotAddress);

    @Query("from Slot s where not exists (from Signaller sig where sig.slot = s) and not exists (from Sensor sen where sen.slot = s)")
    Set<Slot> free();

    @Override
    Set<Slot> findAll();
}
