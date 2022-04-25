package com.cydeo.homeWork;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Q1_Q2_Q3 extends HrTestBase  {



    /*
    Q1:
- Given accept type is Json
- Path param value- US
- When users sends request to /countries
- Then status code is 200
- And Content - Type is Json
- And country_id is US
- And Country_name is United States of America
- And Region_id is 2
     */

    @Test
    public void test1(){

       Response response =  given().accept(ContentType.JSON)
                .and()
               .pathParam("q","US")
                .when()
                .get("/countries/{q}");

        assertEquals(200,response.getStatusCode());
        assertEquals("application/json",response.getContentType());

        JsonPath jsonPath = response.jsonPath();

        String countryID = jsonPath.getString("country_id");
        System.out.println("countryID = " + countryID);

        String countryName = jsonPath.getString("country_name");
        System.out.println("countryName = " + countryName);

        int regionID= jsonPath.getInt("region_id");
        System.out.println("regionID = " + regionID);

        assertEquals("US",countryID);
        assertEquals("United States of America",countryName);
        assertEquals(2,regionID);


    }

    @Test
    public void test2(){
        /*
        Q2:
- Given accept type is Json
- Query param value - q={"department_id":80}
- When users sends request to /employees
- Then status code is 200
- And Content - Type is Json
- And all job_ids start with 'SA'
- And all department_ids are 80
- Count is 25
         */

      Response response =  given().accept(ContentType.JSON)
                .and()
                .queryParam("q","{\"department_id\":80}")
                .when()
                .get("/employees");

      assertEquals(200,response.getStatusCode());
      assertEquals("application/json",response.contentType());

      JsonPath jsonPath = response.jsonPath();

        List<String> allJobIds = jsonPath.getList("items.job_id");
        for (String eachJobID : allJobIds) {
            //System.out.println("eachJobID = " + eachJobID);
            assertTrue(eachJobID.startsWith("SA"));
        }
        List<Integer> departmentIDs = jsonPath.getList("items.department_id");
        for (Integer eachDepartmentID : departmentIDs) {
            assertEquals(80,eachDepartmentID);
        }

        //assertEquals(25, (Integer) response.path("count"));
        assertEquals(25,response.jsonPath().getInt("count"));


    }

    @Test
    public void test3(){
        /*
        Q3:
- Given accept type is Json
-Query param value q= region_id 3
- When users sends request to /countries
- Then status code is 200
- And all regions_id is 3
- And count is 6
- And hasMore is false
- And Country_name are;
Australia,China,India,Japan,Malaysia,Singapore
         */

       Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("q","{\"region_id\": 3}")
                .when()
                .get("/countries");
       assertEquals(200,response.getStatusCode());

       List<Integer> allRegionId = response.path("items.region_id");
        for (Integer eachRegionID : allRegionId) {
            System.out.println("eachRegionID = " + eachRegionID);
            assertEquals(3,eachRegionID);
        }
        assertEquals(6,response.jsonPath().getInt("count"));

        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));
        assertEquals("false",response.jsonPath().getString("hasMore"));
        List<String> countryNames = response.jsonPath().getList("items.country_name");
        System.out.println("countryNames = " + countryNames);
        List<String> expectedName = new ArrayList<>();
        expectedName.addAll(Arrays.asList("Australia", "China", "India", "Japan", "Malaysia", "Singapore"));
        assertEquals(expectedName,countryNames);






    }
}
