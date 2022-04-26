package com.cydeo.day6;

import com.cydeo.POJO.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.geom.RectangularShape;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class SpartanPojoGetRequestTest extends SpartanTestBase {

    @DisplayName("GET one spartan and convert it to Spartan Object")
    @Test
    public void test1(){
      Response response =  given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200).log().body()
                .extract().response();

      // de seriialiaze -->json to pojo (java customer class)
        // 2 different way to do this
        //1. using as() method
        // we conver json response to spartan object with the help of Jackson

        Spartan spartan15 = response.as(Spartan.class);

        System.out.println(spartan15);
        System.out.println(spartan15.getName());
        System.out.println(spartan15.getPhone());

        // second way of deserialize to java
        //2. using JsonPath to deserialize to custom class

        JsonPath jsonPath = response.jsonPath();

        Spartan s15 = jsonPath.getObject("",Spartan.class);
        System.out.println(s15.getName());
    }




}
