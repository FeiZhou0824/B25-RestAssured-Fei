package com.cydeo.day5;

import com.cydeo.utilities.SpartanTestBase;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.vm.ci.meta.MemoryAccessProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class SpartanAPI_vs_DB extends SpartanTestBase {


    @DisplayName("compare one spartan information api vs db")
    @Test
    public void test1(){
       Response response = given().accept(ContentType.JSON)
                .pathParam("id",15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200).extract().response();

       // how to convert json response to map

        Map<String, Object> apiMap = response.as(Map.class);
        System.out.println(apiMap.toString());


    }
}
