package com.cydeo.day10;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class FormulaOneXMLTest {

    //beforeAll is the same thing with beforeClass in testNG
    //http://ergast.com/api/f1/drivers/alonso

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://ergast.com";
        RestAssured.basePath= "/api/f1/";
    }

    @Test
    public void test1(){
        Response response = given()
                .pathParam("driver","alonso")
                .when().get("drivers/{driver}")
                .then().statusCode(200).log().all()
                .extract().response();
        XmlPath xmlPath = response.xmlPath();

        //get given name

        String givenName = xmlPath.getString("MRDate.DriverTable.Driver.GivenName");
        System.out.println("givenName = " + givenName);

        //get family name
        String familyName = xmlPath.getString("MRDate.DriverTable.Driver.FamilyName");
        System.out.println("familyName = " + familyName);

        // how to get attribute value from xml response

        String driverId = xmlPath.getString("MRDate.DriverTable.Driver.@driverId");
        System.out.println("driverId = " + driverId);

        //get the code
        String code = xmlPath.getString("MRDate.DriverTable.Driver.@code");
        System.out.println("code = " + code);

    }


}
