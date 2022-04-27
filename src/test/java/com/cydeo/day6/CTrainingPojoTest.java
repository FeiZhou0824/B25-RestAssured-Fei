package com.cydeo.day6;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CTrainingPojoTest {


        @BeforeAll
        public static void init() {
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
        public void test1() {
           Response response = given().accept(ContentType.JSON)
                    .and()
                    .pathParam("id", 24103)
                    .when()
                    .get("/student/{id}")
                   .then()
                   .statusCode(200)
                   .and()
                   .contentType("application/json;charset=UTF-8")
                   .and()
                   .header("Content-Encoding","gzip")
                   .and()
                   .header("Date",notNullValue())
                   .extract().response();

            //response.prettyPrint();



            JsonPath jsonPath = response.jsonPath();
            String firstName = jsonPath.getString("students[0].firstName");
            int batch = jsonPath.getInt("students[0].batch");
            String major = jsonPath.getString("students[0].major");
            String emailAddress = jsonPath.getString("students[0].contact.emailAddress");
            String companyName = jsonPath.getString("students[0].company.companyName");
            String street = jsonPath.getString("students[0].company.address.street");
            int zipCode = jsonPath.getInt("students[0].company.address.zipCode");

            Assertions.assertEquals("Karole", firstName);
            Assertions.assertEquals(7, batch);
            Assertions.assertEquals("Master of Creative Arts", major);
            Assertions.assertEquals("hassan.lebsack@hotmail.com", emailAddress);
            Assertions.assertEquals("Legacy Integration Analyst", companyName);
            Assertions.assertEquals("6253 Willard Place", street);
            Assertions.assertEquals(28524, zipCode);


        }
    }
