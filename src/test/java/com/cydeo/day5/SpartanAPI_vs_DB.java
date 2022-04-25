package com.cydeo.day5;

import com.cydeo.utilities.DBUtils;

import com.cydeo.utilities.SpartanTestBase;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;


import static org.hamcrest.MatcherAssert.assertThat;
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

        String query = "SELECT SPARTAN_ID, NAME, GENDER,PHONE \n" +
                "FROM SPARTANS\n" +
                "WHERE SPARTAN_ID =15";
        Map<String, Object> dbMaP = DBUtils.getRowMap(query);
        System.out.println(dbMaP);

        //compare api vs db
        assertThat(apiMap.get("id").toString(),is(dbMaP.get("SPARTAN_ID").toString()));
        assertThat(apiMap.get("name"),is(dbMaP.get("NAME")));
        assertThat(apiMap.get("gender"),is(dbMaP.get("GENDER")));
        assertThat(apiMap.get("phone").toString(),is(dbMaP.get("PHONE").toString()));




    }


}
