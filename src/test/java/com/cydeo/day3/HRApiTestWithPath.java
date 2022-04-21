package com.cydeo.day3;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.image.RescaleOp;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HRApiTestWithPath extends HrTestBase {


    @DisplayName("GET request to countries with Path method")
    @Test
    public void test1(){
      Response response =  given()
                .accept(ContentType.JSON)
                .queryParam("q","{\"region_id\":2}")
                .when()
                .get("/countries");
      //response.prettyPrint();

       System.out.println("response.path().toString() = " + response.path("limit").toString());
        System.out.println("response.path(\"hasMore\").toString() = " + response.path("hasMore").toString());
        System.out.println("response.path(\"item[1].country_id\") = " + response.path("items[1].country_id"));

        //print 4th element country name
        System.out.println("response.path(\"items.country_name\") = " + response.path("items[3].country_name"));

        //assert that in the reponse all region _ids are 2
        List<Integer> allRegionIds = response.path("items.region_id");

        // save all the regins ids inside
        for (Integer eachRegionId : allRegionIds) {
            System.out.println(eachRegionId);
        }


    }

    /*
        send a GET request o employees endpoint, filter only Job_id IT_PROG
        then assert that all job_ids are IT_PROG
     */
    @Test
    public void test2(){

       Response response = given()
               .accept(ContentType.JSON)
               .queryParam("q","{\"job_id\": \"IT_PROG\"}")
                .get("/employees");
       assertEquals(200, response.getStatusCode());

       // save all the job id inside the lisg
        List<String> allJobsId = response.path("items.job_id");
        //assert one by one that are equal to :IT PROG
        for (String eachJobId : allJobsId) {
            assertEquals("IT_PROG",eachJobId);
            System.out.println(eachJobId);
        }


    }

}
