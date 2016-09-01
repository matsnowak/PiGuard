package com.matsnowak.piguard.controllers;

import com.matsnowak.piguard.main.ApiUrls;
import com.matsnowak.piguard.main.ServerApplication;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Mateusz Nowak on 24.08.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServerApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public abstract class AbstractControllerTest {

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    protected String mapping(String endpoint) {
        return ApiUrls.API_ROOT + endpoint;
    }
}
