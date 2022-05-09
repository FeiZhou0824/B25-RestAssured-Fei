package com.cydeo.day10;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

    }
}
