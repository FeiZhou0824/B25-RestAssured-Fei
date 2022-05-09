package com.cydeo.day10;


import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class SpartanXMLTest extends SpartanTestBase {

    //we will ask for xml result
    // assert status code
    //assert content type xml
    //verify first name

    @Test
    public void test1(){

        given()
                .accept(ContentType.XML)
                .and()
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType("application/xml")
                .body("List.item[0].name",is("Meade"))
                .body("List.item[0].gender",is("Male"))
                .log().all();
    }

    @Test
    public void test2(){
      Response response =  given()
                .accept(ContentType.XML)
                .and()
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans");

      // get response xml body /payload and save inside the xmlpath object

        XmlPath xmlPath = response.xmlPath();
        String name = xmlPath.getString("List.item[0].name");
        System.out.println("name = " + name);

        //get me 3rd spartan id number

        int id = xmlPath.getInt("List.item[2].id");
        System.out.println("id = " + id);

    }


}