package com.cydeo.day7;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class PutAndPatchRequestDemo extends SpartanTestBase {


    @DisplayName("PUT request to one spartan for update with Map")
    @Test
    public void test1() {
        // just like post request we have different options to send json body, we will go with map

        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("gender", "Male");
        putRequestMap.put("name", "Severus Snape");
        putRequestMap.put("phone", 8877445596l);

        JsonPath jsonPath = given().contentType(ContentType.JSON)
                .body(putRequestMap).log().all()
                .and().pathParam("id", 85)
                .when().put("api/spartans/{id}")
                .then().statusCode(204).extract().jsonPath();
        //int idFromPut =  jsonPath.getInt("id");

        //HW
        //send a GET request to id number and make sure fields are updated
        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("id", 85)
                .and().get("/api/spartans/{id}");

        assertThat(response.path("id"), is(85));
        assertThat(response.path("name"), is("Severus Snape"));
        assertThat(response.path("gender"), is("Male"));
        assertThat(response.path("phone"), is(8877445596l));
    }

    @DisplayName("PATCH request to one spartan for update with Map")
    @Test
    public void test2() {
        // just like post request we have different options to send json body, we will go with map

        Map<String, Object> patchRequestMap = new LinkedHashMap<>();

        patchRequestMap.put("name", "SeverusTyler");


        given().contentType(ContentType.JSON)
                .body(patchRequestMap).log().all()
                .and().pathParam("id", 85)
                .when().patch("api/spartans/{id}")
                .then().statusCode(204);

        //HW
        //send a GET request to id number and make sure fields are updated



    }

    @DisplayName("DELETE one spartan")
    @Test
    public void test3(){

        int idToDelete = 55;
        given().pathParam("id",idToDelete)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);
        // we can send GET request to id number and verify status code is 404

        given().pathParam("id",idToDelete)
                .when().get("/api/spartans/{id}")
                .then().statusCode(404);
    }

}
