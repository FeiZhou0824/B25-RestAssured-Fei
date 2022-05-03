package com.cydeo.day8;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class BookItTest {

    @BeforeAll
    public static void init(){

        baseURI = "https://cybertek-reservation-api-qa.herokuapp.com";
    }

    String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMTMwOSIsImF1ZCI6InN0dWRlbnQtdGVhbS1tZW1iZXIifQ.Lkjp9IzmVSdznbpzxoPoilDDf3obGgnT2C4kkg01ZUI";

    @DisplayName("GET /api/campuses")
    @Test
    public void test1(){
        // how to pass bearer token for bookit?
        given()
                .accept(ContentType.JSON)
                .header("Authorization",accessToken)
                .accept(ContentType.JSON)
                .when()
                .get("/api/campuses")
                .then().statusCode(200)
                .log().all();


    }
    @DisplayName("GET /api/users/me")
    @Test
    public void test2(){
        // how to pass bearer token for bookit?
        given()
                .accept(ContentType.JSON)
                .header("Authorization",accessToken)
                .accept(ContentType.JSON)
                .when()
                .get("/api/users/me")
                .then().statusCode(200)
                .log().all();


    }


}
