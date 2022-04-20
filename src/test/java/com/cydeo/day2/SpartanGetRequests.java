package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpartanGetRequests {

    String url = "http://3.95.13.229:8000";


    /*
    Given Accept type is application/json
    when user send GET request to /api/spartans end point
    Then status code must be 200
    and response content type must be application/json
     */
    @Test
    public void test1(){
        Response response = RestAssured.
                given().
                        accept(ContentType.JSON)
                .when()
                        .get(url+"/api/spartans");

        //print the status code
        System.out.println("response.statusCode() = " + response.statusCode());
        //print the contentType
        System.out.println("response.contentType() = " + response.contentType());

        //how to test API?
        //Verify Status code is 200
        Assertions.assertEquals(200,response.statusCode());

        //verify content type is application/json
        Assertions.assertEquals("application/json", response.contentType());

        /*
        Given Accept header is application/json
        When user send GET request to  /api/spartans/3
        Then status code must be 200
        And response content type must be application/json
        And json body should contain 'Fidole'
     */

    }

    @Test
    public void test2(){
        Response response = RestAssured.
                given().accept(ContentType.JSON)
                .when()
                .get(url + "/api/spartans/3");

        Assertions.assertEquals(200,response.getStatusCode());
        Assertions.assertEquals("application/json",response.contentType());

        response.body().asString().contains("Fidole");
        Assertions.assertTrue(response.body().asString().contains("Fidole"));

    }

    /*
    /*
        Given no headers provided
        When Users send GET request to /api/hello
        Then response status code should be 200
        And Content type header should be “text/plain;charset=UTF-8”
        And header should contain date
        And Content-Length should be 17
        And body should be “Hello from Sparta"
        */

    @Test
    public void test3(){

    }

}
