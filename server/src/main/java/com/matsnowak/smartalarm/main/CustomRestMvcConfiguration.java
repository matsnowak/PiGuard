package com.matsnowak.smartalarm.main;

import com.matsnowak.smartalarm.validators.BeforeSaveSlotValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * Created by Mateusz on 27.07.2016.
 */
@Configuration
public class CustomRestMvcConfiguration {

    public static final String API_BASE_PATH = "/api";

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {

        return new RepositoryRestConfigurerAdapter() {

            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.setBasePath(API_BASE_PATH);
            }

            @Override
            public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
                validatingListener.addValidator("beforeSave", new BeforeSaveSlotValidator());
            }
        };
    }
}
