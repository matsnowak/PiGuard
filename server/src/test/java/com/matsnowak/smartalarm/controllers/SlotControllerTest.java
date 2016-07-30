package com.matsnowak.smartalarm.controllers;

import com.matsnowak.smartalarm.main.ApiUrls;
import com.matsnowak.smartalarm.main.AutoConfig;
import com.matsnowak.smartalarm.main.CustomRestMvcConfiguration;
import com.matsnowak.smartalarm.main.ServerApplication;
import com.matsnowak.smartalarm.model.CommunicationSlot;
import com.matsnowak.smartalarm.model.CommunicationSlotAddress;
import com.matsnowak.smartalarm.model.CommunicationSlotState;
import com.matsnowak.smartalarm.repositories.CommunicationSlotRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.matsnowak.smartalarm.model.CommunicationSlotAddress.*;
import static com.matsnowak.smartalarm.model.CommunicationSlotState.*;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;

/**
 * Created by Mateusz Nowak on 12.07.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServerApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class SlotControllerTest {

    @Autowired
    AutoConfig autoConfig;

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    @Test
    public void findAll_by_GET_returnList_OK_200() throws Exception {
        when()
                .get(mapping("/slots"))
        .then()
                .statusCode(SC_OK)
                .body(not(empty()));
    }

    @Test
    public void findById_by_GET_returnBody_OK_200() throws Exception {
        when()
                .get(mapping("/slots/1"))
        .then()
                .statusCode(SC_OK)
                .body("address", equalTo(CommunicationSlotAddress.SLOT_1.name()))
                .body("state", equalTo(NOT_USED.name()));


    }

    @Test
    public void findById_by_GET_notExisting_NotFound_404() throws Exception {
        when()
                .get(mapping("/slots/99"))
        .then()
                .statusCode(SC_NOT_FOUND)
                .body(isEmptyOrNullString());
    }

    @Test
    public void save_by_PUT_onCollection_notAllowed_405() throws Exception {
        given()
                .contentType(ContentType.JSON)
                .body(slot(SLOT_11, INPUT))
        .when()
                .put(mapping("/slots"))
        .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void save_by_PUT_onEntity_existing_notAllowed_405() throws Exception {
        given()
                .contentType(ContentType.JSON)
                .body(slot(SLOT_2, INPUT))
        .when()
                .put(mapping("/slots/2"))
        .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void save_by_PUT_onEntity_notExisting_notAllowed_405() throws Exception {
        given()
                .contentType(ContentType.JSON)
                .body(slot(SLOT_12, INPUT))
        .when()
                .put(mapping("/slots/199"))
        .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void save_by__POST_onCollection_notAllowed_405() throws Exception {
        given()
                .contentType(ContentType.JSON)
                .body(slot(SLOT_13, INPUT))
        .when()
                .post(mapping("/slots"))
        .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void save_by_POST_onEntity_existing_notAllowed_405() throws Exception {
        given()
                .contentType(ContentType.JSON)
                .body(slot(SLOT_14, INPUT))
        .when()
                .post(mapping("/slots/3"))
        .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void save_by_POST_onEntity_notExisting_notAllowed_405() throws Exception {
        given()
                .contentType(ContentType.JSON)
                .body(slot(SLOT_15, INPUT))
        .when()
                .post(mapping("/slots/299"))
        .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void save_by_PATCH_onCollection_notAllowed_405() throws Exception {
        when()
                .patch(mapping("/slots"))
        .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void save_by_PATCH_onEntity_existing_onlyState_OK_200() throws Exception {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"state\" : \"INPUT\" }")
        .when()
                .patch(mapping("/slots/4"))
        .then()
                .statusCode(SC_OK)
                .body("state", equalTo(INPUT.name()))
                .body("address", equalTo(SLOT_4.name()));
    }


    @Test
    public void save_by_PATCH_onEntity_notExisting_NotFound_404() throws Exception {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"state\" : \"INPUT\" }")
        .when()
                .patch(mapping("/slots/399"))
        .then()
                .statusCode(SC_NOT_FOUND)
                .body(isEmptyOrNullString());
    }

    @Test
    public void save_by_PATCH_onEntity_existing_addressChanged_badRequest_400() throws Exception {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"address\" : \"SLOT_16\" }")
        .when()
                .patch(mapping("/slots/5"))
        .then()
                .statusCode(SC_BAD_REQUEST);
    }

    @Test
    public void delete_by_DELETE_onCollection_notAllowed_405() throws Exception {
        when()
                .delete(mapping("/slots"))
                .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void delete_by_DELETE_onEntity_notAllowed_405() throws Exception {
        when()
                .delete(mapping("/slots/1"))
                .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }


    private String mapping(String endpoint) {
        return ApiUrls.API_ROOT + endpoint;
    }

    private CommunicationSlot slot(CommunicationSlotAddress address, CommunicationSlotState state) {
        return new CommunicationSlot(address, state);
    }


}