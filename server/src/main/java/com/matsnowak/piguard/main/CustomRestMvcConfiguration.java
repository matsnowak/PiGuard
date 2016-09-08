package com.matsnowak.piguard.main;

import com.matsnowak.piguard.model.*;
import org.hibernate.jpa.boot.internal.SettingsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * Created by Mateusz Nowak on 27.07.2016.
 */
@Configuration
@ComponentScan("com.matsnowak.piguard.controllers")
public class CustomRestMvcConfiguration {

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {

        return new RepositoryRestConfigurerAdapter() {

            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.setBasePath(ApiUrls.API_ROOT);
                config.exposeIdsFor(
                        ArmedZone.class,
                        Sensor.class,
                        Settings.class,
                        Signaller.class,
                        Slot.class,
                        Zone.class);
            }

        };
    }
}
