package com.matsnowak.smartalarm.main;

import com.google.common.collect.Lists;
import com.matsnowak.smartalarm.core.Platform;
import com.matsnowak.smartalarm.model.Slot;
import com.matsnowak.smartalarm.repositories.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.LinkedList;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {
	"com.matsnowak.smartalarm.repositories",
})
@Import(RepositoryRestMvcConfiguration.class)
@EntityScan(basePackages = "com.matsnowak.smartalarm.model")
@ComponentScan(basePackages = "com.matsnowak.smartalarm")
public class ServerApplication {

	@Autowired
	AutoConfig autoConfig;

	@Autowired
	Platform platform;

	@Autowired
	SlotRepository slotRepository;


	@PostConstruct
	public void init() {
		autoConfig.init();
		platform.startMonitoring(Lists.newArrayList(slotRepository.findAll()));
	}

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
}
