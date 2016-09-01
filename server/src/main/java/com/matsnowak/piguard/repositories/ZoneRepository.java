package com.matsnowak.piguard.repositories;

import com.matsnowak.piguard.main.ApiUrls;
import com.matsnowak.piguard.model.Zone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Mateusz on 30.06.2016.
 */
@RepositoryRestResource(collectionResourceRel = ApiUrls.API_ZONES, path = ApiUrls.API_ZONES)
public interface ZoneRepository extends CrudRepository<Zone, Integer> {

}
