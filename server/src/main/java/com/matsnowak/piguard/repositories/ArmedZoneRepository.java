package com.matsnowak.piguard.repositories;

import com.matsnowak.piguard.main.ApiUrls;
import com.matsnowak.piguard.model.ArmedZone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Mateusz Nowak on 24.08.2016.
 */

@RepositoryRestResource(collectionResourceRel = ApiUrls.API_ARMED_ZONES, path = ApiUrls.API_ARMED_ZONES)
public interface ArmedZoneRepository extends CrudRepository<ArmedZone, Integer> {
}
