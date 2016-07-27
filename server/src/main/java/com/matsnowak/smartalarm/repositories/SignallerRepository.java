package com.matsnowak.smartalarm.repositories;

import com.matsnowak.smartalarm.model.Signaller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

/**
 * Created by Mateusz on 24.06.2016.
 */
@RepositoryRestResource(collectionResourceRel = "signallers", path = "signallers")
public interface SignallerRepository extends CrudRepository<Signaller, Integer> {

}
