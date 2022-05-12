package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class JsonSchemaValidationTest extends SpartanAuthTestBase {

    //get the file under the resources

    @DisplayName("GET request to verify one spartan against to schema")

    @Test
    public void test1() {

        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 50)
                .and()
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans/{id}")
                .then().statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"))  // Schema is under the resources
                .log().all();

    }


    // how to get the file if it is not under the resources
    @Test
    public void test2() {

        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans/")
                .then().statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/cydeo/day10/allSpartansSchema.json"))); // schema is not under the resources
    }

    //homework
    //put your post json schema under day10
    //post one spartan using dynamic input (name, gender,phone)
    //verify your post response matching with json schema

}
