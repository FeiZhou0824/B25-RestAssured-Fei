package com.cydeo.day4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CTrainingTests {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://api.cybertektraining.com";
    }



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

                using JsonPath
             */

    //@DisplayName("")
    @Test
    public void test1(){
       Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id",24103)
                .when()
                .get("/student/{id}");

       response.prettyPrint();

        Assertions.assertEquals(200,response.getStatusCode());
        Assertions.assertEquals("application/json;charset=UTF-8",response.contentType());

        //how to get any header value?
        Assertions.assertEquals("gzip",response.header("Content-Encoding"));

        // how to verify header exists or not
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        JsonPath jsonPath = response.jsonPath();
        String firstName = jsonPath.getString("students[0].firstName");
        int batch = jsonPath.getInt("students[0].batch");
        String major = jsonPath.getString("students[0].major");
        String emailAddress = jsonPath.getString("students[0].contact.emailAddress");
        String companyName = jsonPath.getString("students[0].company.companyName");
        String street = jsonPath.getString("students[0].company.address.street");
       int zipCode = jsonPath.getInt("students[0].company.address.zipCode");

        Assertions.assertEquals("Karole",firstName);
        Assertions.assertEquals(7,batch);
        Assertions.assertEquals("Master of Creative Arts",major);
        Assertions.assertEquals("hassan.lebsack@hotmail.com",emailAddress);
        Assertions.assertEquals("Legacy Integration Analyst",companyName);
        Assertions.assertEquals("6253 Willard Place",street);
        Assertions.assertEquals(28524,zipCode);






    }
}
