package com.cydeo.day3;

import com.cydeo.utilities.HrTestBase;
import com.cydeo.utilities.SpartanTestBase;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestWithPath extends SpartanTestBase {

     /*
     Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936
   */

    @Test
    public void test1(){

    }




}
