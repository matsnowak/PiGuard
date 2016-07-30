package com.matsnowak.smartalarm.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {
	"com.matsnowak.smartalarm.repositories",
})
@Import(RepositoryRestMvcConfiguration.class)
@EntityScan(basePackages = "com.matsnowak.smartalarm.model")
public class ServerApplication {

	@Autowired
	AutoConfig autoConfig;


	@PostConstruct
	public void init() {
		autoConfig.init();
	}

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
}
