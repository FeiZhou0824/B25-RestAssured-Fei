package com.cydeo.day6;

import com.cydeo.POJO.Employee;
import com.cydeo.POJO.Region;
import com.cydeo.utilities.HrTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class HRPojoGetRequestTest extends HrTestBase {

    @Test
    public void test1(){

        JsonPath jsonPath = get("/regions").then().statusCode(200).extract().jsonPath();

        Region region2 = jsonPath.getObject("items[1]",Region.class);
        System.out.println("region2.getRegion_id() = " + region2.getRegionId());
        System.out.println("region2.getRegion_name() = " + region2.getRegionName());
        System.out.println("region2.getLinks().get(0).getHref() = " + region2.getLinks().get(0).getHref());
    }

    @Test
    public void test2(){

       JsonPath jsonPath =  get("employees").then().statusCode(200).extract().jsonPath();

       Employee em1 = jsonPath.getObject("items[0]", Employee.class);



        System.out.println("em1.getJobId() = " + em1.getJobId());
        System.out.println("em1.getFirstName() = " + em1.getFirstName());
        System.out.println("em1.getLastName() = " + em1.getLastName());
        System.out.println("em1.getSalary() = " + em1.getSalary());


    }




}
