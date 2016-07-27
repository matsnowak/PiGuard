package com.matsnowak.smartalarm.controllers;

import com.matsnowak.smartalarm.main.AutoConfig;
import com.matsnowak.smartalarm.main.CustomRestMvcConfiguration;
import com.matsnowak.smartalarm.main.ServerApplication;
import com.matsnowak.smartalarm.model.CommunicationSlot;
import com.matsnowak.smartalarm.model.CommunicationSlotAddress;
import com.matsnowak.smartalarm.model.CommunicationSlotState;
import com.matsnowak.smartalarm.repositories.CommunicationSlotRepository;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static io.restassured.RestAssured.when;
import static org.apache.http.HttpStatus.SC_METHOD_NOT_ALLOWED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

/**
 * Created by Mateusz on 12.07.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServerApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class CommunicationSlotControllerTest {

    private CommunicationSlot testSlot() {
        return new CommunicationSlot(CommunicationSlotAddress.SLOT_1, CommunicationSlotState.INPUT);
    }

    private String mapping(String endpoint) {
        return CustomRestMvcConfiguration.API_BASE_PATH + endpoint;
    }

    @Autowired
    CommunicationSlotRepository slotsRepository;

    @Autowired
    AutoConfig autoConfig;


    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
//        slotsRepository.deleteAll();
//        slotsRepository.save(autoConfig.slots());
    }

    @Test
    public void findAll_GET_returnList_OK_200() throws Exception {
        when()
                .get(mapping("/slots"))
        .then()
                .statusCode(SC_OK)
                .body(not(empty()));
    }

    @Test
    public void findById_GET_returnBody_OK_200() throws Exception {
        when()
                .get(mapping("/slots/2")) // TODO fix it
                .then()
                .statusCode(SC_OK)
                .body(not(empty()));
    }

    @Test
    public void PUT_onCollection_notAllowed_405() throws Exception {
        when()
                .put(mapping("/slots"))
                .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void PUT_onSingle_notAllowed_405() throws Exception {
        when()
                .put(mapping("/slots"))
                .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void insert_POST_onCollection_notAllowed_405() throws Exception {
        when()
                .post(mapping("/slots"))
                .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void insert_POST_onSingle_notAllowed_405() throws Exception {
        when()
                .post(mapping("/slots/1"))
                .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void PATCH_onCollection_notAllowed_405() throws Exception {
        when()
                .patch(mapping("/slots"))
                .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void PATCH_onSingle_notAllowed_405() throws Exception {
        when()
                .patch(mapping("/slots"))
                .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void DELETE_onCollection_notAllowed_405() throws Exception {
        when()
                .delete(mapping("/slots"))
                .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void DELETE_onSingle_notAllowed_405() throws Exception {
        when()
                .delete(mapping("/slots/1"))
                .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }


}