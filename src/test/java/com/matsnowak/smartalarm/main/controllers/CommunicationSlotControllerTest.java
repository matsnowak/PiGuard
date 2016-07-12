package com.matsnowak.smartalarm.main.controllers;

import com.matsnowak.smartalarm.main.CoreApplication;
import com.matsnowak.smartalarm.main.repositories.CommunicationSlotRepository;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static io.restassured.RestAssured.when;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by Mateusz on 12.07.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CoreApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class CommunicationSlotControllerTest {


    @Value("${local.server.port}")
    int port;

    @Autowired
    CommunicationSlotRepository communicationSlotRepository;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;

    }

    @Test
    public void name() throws Exception {
        when()
                .get("/communicationSlots")
        .then()
                .statusCode(SC_OK)
                .body(not(empty()));
    }


}