package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeGetRequest {

    // beforeAll is the same thing with beforeClass in testing
    @BeforeAll
    public static void init (){
        RestAssured.baseURI="http://3.95.13.229:8000";

    }

    @DisplayName("GET all spartans")
    @Test
    public void test1() {
        Response response = given().
                accept(ContentType.JSON)
                .when()
                .get("/api/spartans");

        //print the status code
        System.out.println("response.statusCode() = " + response.statusCode());
        //print the contentType
        System.out.println("response.contentType() = " + response.contentType());

        //how to test API?
        //Verify Status code is 200
        assertEquals(200, response.statusCode());

        //verify content type is application/json
        assertEquals("application/json", response.contentType());

    }

    /*TASK
    Given Accept type application/xml
    When user send GET request to /api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml;charset=UTF-8;
    */


    @DisplayName("GET request with Accept XML individual Spartan")
    @Test
    public void testTask(){

        Response response = given()
                .accept(ContentType.XML)
                .when().get("/api/spartans/10");

        assertEquals(406, response.getStatusCode());

        System.out.println("response.header(\"Content-Type\") = " + response.header("Content-Type"));

        assertEquals("application/xml;charset=UTF-8",response.header("Content-Type"));

        response.prettyPrint();



    }
}
