package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class HrTestBase {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI ="http://3.95.13.229:1000/ords/hr";
    }
}
