package com.cydeo.homeWork.zipCodeHomeWork;

import com.cydeo.homeWork.zipCodeHomeWork.POJO.ZipCode;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ZipcodeHomeWorkTest {

    @BeforeAll
    public static void init(){ RestAssured.baseURI="https://api.zippopotam.us";}


    @Test
    public void test1(){
        /*
        Given Accept application/json
And path zipcode is 22031
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And Server header is cloudflare
And Report-To header exists
And body should contains following information
    post code is 22031
    country  is United States
    country abbreviation is US
    place name is Fairfax
    state is Virginia
    latitude is 38.8604
         */

      Response response=  given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("zipcode",22031)
                .when()
                .get("us/{zipcode}")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .header("Server",is("cloudflare"))
                .header("Report-To",notNullValue())
                .extract().response();
     // response.prettyPrint();


/*
And body should contains following information
    post code is 22031
    country  is United States
    country abbreviation is US
    place name is Fairfax
    state is Virginia
    latitude is 38.8604
 */
        ZipCode zipCode = response.as(ZipCode.class);

        assertEquals(22031,zipCode.getPostCode());
        assertEquals("United States",zipCode.getCountry());
        assertEquals("Fairfax",zipCode.getPlaces().get(0).getPlaceName());
        assertEquals("Virginia",zipCode.getPlaces().get(0).getState());
        assertEquals(38.8604,zipCode.getPlaces().get(0).getLatitude());
        System.out.println("zipCode.getPlaces().get(0).getLatitude() = " + zipCode.getPlaces().get(0).getLatitude());


    }

    @Test
    public void test2(){

        /*
        Given Accept application/json
And path zipcode is 50000
When I send a GET request to /us endpoint
Then status code must be 404
And content type must be application/json
         */

        given().accept(ContentType.JSON)
                .and()
                .pathParam("zipcode",50000)
                .when()
                .get("/us{zipcode}")
                .then()
                .statusCode(404)
                .contentType("application/json");

    }

    @Test
    public void test3(){
        /*
        Given Accept application/json
And path state is va
And path city is farifax
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And payload should contains following information
    country abbreviation is US
    country  United States
    place name  Fairfax
    each places must contains fairfax as a value
    each post code must start with 22
         */
    }

}
