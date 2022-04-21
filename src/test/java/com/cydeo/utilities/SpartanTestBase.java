package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class SpartanTestBase {

    // beforeAll is the same thing with beforeClass in testing
    @BeforeAll
    public static void init (){
        RestAssured.baseURI="http://3.95.13.229:8000";

    }
}
