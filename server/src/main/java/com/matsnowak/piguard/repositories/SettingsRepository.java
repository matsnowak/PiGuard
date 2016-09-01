package com.matsnowak.piguard.repositories;

import com.matsnowak.piguard.main.ApiUrls;
import com.matsnowak.piguard.model.Settings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by Mateusz Nowak on 01.09.2016.
 */
@RepositoryRestResource(collectionResourceRel = ApiUrls.API_SETTINGS, path = ApiUrls.API_SETTINGS)
public interface SettingsRepository extends CrudRepository<Settings, Integer> {
    @RestResource(exported = false)
    @Override
    <S extends Settings> Iterable<S> save(Iterable<S> entities);

    @RestResource(exported = false)
    @Override
    void delete(Integer integer);

    @RestResource(exported = false)
    @Override
    void delete(Settings entity);

    @RestResource(exported = false)
    @Override
    void delete(Iterable<? extends Settings> entities);

    @RestResource(exported = false)
    @Override
    void deleteAll();
}
