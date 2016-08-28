package com.matsnowak.smartalarm.controllers;

import org.junit.Test;

import static io.restassured.RestAssured.when;
import static org.apache.http.HttpStatus.SC_OK;

/**
 * Created by Mateusz Nowak on 31.07.2016.
 */


public class SensorControllerTest extends AbstractControllerTest{

    @Test
    public void findAll_by_GET_returnList_OK_200() throws Exception {
        when()
                .get(mapping("/sensors"))
        .then()
                .statusCode(SC_OK);
    }


}
