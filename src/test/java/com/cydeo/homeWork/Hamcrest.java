package com.cydeo.homeWork;


import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;


import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class Hamcrest {


//send a get request to student id 24103 as a path parameter and accept header application/json
//verify status code=200
// /content type=application/json;charset=UTF-8
// /Content-Encoding = gzip
//verify Date header exists
//assert that
            /*
                firstName Karole
                batch 7
                major Master of Creative Arts
                emailAddress hassan.lebsack@hotmail.com
                companyName Legacy Integration Analyst
                street 6253 Willard Place
                zipCode 28524

             */

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://api.cybertektraining.com";
    }

    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                .and()
                .pathParam("id",24103)
                .when()
                .get("/student/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("Content-Encoding",is("gzip"))
                .and()
                .header("Date",notNullValue())
                .body("students[0].firstName",is("Karole"),"students[0].batch",is(7),"students[0].major",is("Master of Creative Arts"));





    }



}