package com.matsnowak.smartalarm.controllers;

import com.matsnowak.smartalarm.main.ApiUrls;
import com.matsnowak.smartalarm.main.ServerApplication;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static io.restassured.RestAssured.when;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

/**
 * Created by Mateusz Nowak on 31.07.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServerApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class SensorControllerTest {

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    private String mapping(String endpoint) {
        return ApiUrls.API_ROOT + endpoint;
    }

    @Test
    public void findAll_by_GET_returnList_OK_200() throws Exception {
        when()
                .get(mapping("/sensors"))
        .then()
                .statusCode(SC_OK);
    }


}
