package com.matsnowak.piguard.repositories;

import com.google.common.collect.Lists;
import com.matsnowak.piguard.model.ArmedZone;
import com.matsnowak.piguard.model.Zone;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by Mateusz Nowak on 24.08.2016.
 */

public class ArmedZoneRepositoryTest extends AbstractRepositoryTest{

    @Autowired
    ArmedZoneRepository armedZoneRepository;

    @Autowired
    ZoneRepository zoneRepository;


    @Test
    public void shouldNotDeleteZoneWhenArmedZoneDeleted() throws Exception {
        // given
        Zone zone1 = zoneRepository.save(Zone.create("zone1", Collections.emptySet(), Collections.emptySet()));
        zoneRepository.save(Zone.create("zone2", Collections.emptySet(), Collections.emptySet()));
        zoneRepository.save(Zone.create("zone3", Collections.emptySet(), Collections.emptySet()));

        ArmedZone savedZone = armedZoneRepository.save(ArmedZone.create(zone1, LocalDateTime.now()));

        // when
        armedZoneRepository.delete(savedZone);

        // then
        ArrayList<Zone> readedZones = Lists.newArrayList(zoneRepository.findAll());
        assertThat(readedZones).hasSize(3);

        assertThat(Lists.newArrayList(armedZoneRepository.findAll())).isEmpty();

    }
}