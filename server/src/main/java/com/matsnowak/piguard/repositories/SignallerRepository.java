package com.matsnowak.piguard.repositories;

import com.matsnowak.piguard.model.Signaller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Mateusz on 24.06.2016.
 */
@RepositoryRestResource(collectionResourceRel = "signallers", path = "signallers")
public interface SignallerRepository extends CrudRepository<Signaller, Integer> {

}
