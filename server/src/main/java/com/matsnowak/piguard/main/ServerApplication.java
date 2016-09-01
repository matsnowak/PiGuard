package com.matsnowak.piguard.main;

import com.matsnowak.piguard.core.Platform;
import com.matsnowak.piguard.platforms.PlatformFactory;
import com.matsnowak.piguard.repositories.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {
		"com.matsnowak.piguard.repositories",
})
@Import(RepositoryRestMvcConfiguration.class)
@EntityScan(basePackages = "com.matsnowak.piguard.model")
@ComponentScan(basePackages = "com.matsnowak.piguard")
public class ServerApplication {

	@Autowired
	AutoConfig autoConfig;


	@Autowired
	SlotRepository slotRepository;


	@Bean
	Platform platform() {
		return PlatformFactory.newInstance();
	}


	@PostConstruct
	public void init() {
		autoConfig.init();
	}

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
}
