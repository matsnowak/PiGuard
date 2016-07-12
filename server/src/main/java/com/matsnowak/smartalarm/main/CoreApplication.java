package com.matsnowak.smartalarm.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages = {
        "com.matsnowak.smartalarm.model"
})
@Configuration
@ComponentScan
public class CoreApplication {

    @Autowired AutoConfig autoConfig;


    @PostConstruct
    public void init() {
        autoConfig.init();;
    }

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);

	}
}
