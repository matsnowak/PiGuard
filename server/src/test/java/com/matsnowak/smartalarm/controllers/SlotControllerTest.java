package com.matsnowak.smartalarm.controllers;

import com.matsnowak.smartalarm.model.Slot;
import com.matsnowak.smartalarm.model.SlotAddress;
import io.restassured.http.ContentType;
import org.junit.Test;

import static com.matsnowak.smartalarm.model.SlotAddress.*;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;

/**
 * Created by Mateusz Nowak on 12.07.2016.
 */
public class SlotControllerTest  extends AbstractControllerTest{

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
                .body("address", equalTo(SlotAddress.SLOT_1.name()));


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
                .body(slot(SLOT_11))
        .when()
                .put(mapping("/slots"))
        .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void save_by_PUT_onEntity_existing_notAllowed_405() throws Exception {
        given()
                .contentType(ContentType.JSON)
                .body(slot(SLOT_2))
        .when()
                .put(mapping("/slots/2"))
        .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void save_by_PUT_onEntity_notExisting_notAllowed_405() throws Exception {
        given()
                .contentType(ContentType.JSON)
                .body(slot(SLOT_12))
        .when()
                .put(mapping("/slots/199"))
        .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void save_by__POST_onCollection_notAllowed_405() throws Exception {
        given()
                .contentType(ContentType.JSON)
                .body(slot(SLOT_13))
        .when()
                .post(mapping("/slots"))
        .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void save_by_POST_onEntity_existing_notAllowed_405() throws Exception {
        given()
                .contentType(ContentType.JSON)
                .body(slot(SLOT_14))
        .when()
                .post(mapping("/slots/3"))
        .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void save_by_POST_onEntity_notExisting_notAllowed_405() throws Exception {
        given()
                .contentType(ContentType.JSON)
                .body(slot(SLOT_15))
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
    public void save_by_PATCH_onEntity_existing_addressChanged_notAllowed_405() throws Exception {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"address\" : \"SLOT_16\" }")
        .when()
                .patch(mapping("/slots/5"))
        .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
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


    private Slot slot(SlotAddress address) {
        return new Slot(address);
    }


}