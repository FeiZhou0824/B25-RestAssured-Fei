package com.cydeo.day6;

import com.cydeo.POJO.Student;
import com.cydeo.POJO.Students;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

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

            Students students = response.as(Students.class);
          //  System.out.println(students);

            Student student1 = students.getStudents().get(0);

           // JsonPath jsonPath = response.jsonPath();

            assertEquals("Karole",student1.getFirstName());
            assertEquals(7, student1.getBatch());
            assertEquals("Master of Creative Arts", student1.getMajor());
            assertEquals("hassan.lebsack@hotmail.com", student1.getContact().getEmailAddress());
            assertEquals("Legacy Integration Analyst", student1.getCompany().getCompanyName());
            assertEquals("6253 Willard Place", student1.getCompany().getAddress().getStreet());
            assertEquals(28524, student1.getCompany().getAddress().getZipCode());


        }
    }
