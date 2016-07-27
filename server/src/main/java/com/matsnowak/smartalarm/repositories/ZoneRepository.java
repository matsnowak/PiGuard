package com.matsnowak.smartalarm.repositories;

import com.matsnowak.smartalarm.model.Zone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

/**
 * Created by Mateusz on 30.06.2016.
 */
@RepositoryRestResource(collectionResourceRel = "zones", path = "zones")
public interface ZoneRepository extends CrudRepository<Zone, Integer> {

}
