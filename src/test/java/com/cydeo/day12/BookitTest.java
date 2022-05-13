package com.cydeo.day12;


import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Map;

public class BookitTest {

    //create a method to read bookitqa3 excel file information
    //use those information as an email and password to send a get request to /sign endpoint
    //verify you got 200 for each request
    //print accesstoken for each request
    //https://cybertek-reservation-api-qa3.herokuapp.com

    public static List<Map<String,String>> getExcelData() {

        ExcelUtil bookitFile = new ExcelUtil("src/test/resources/BookItQa3.xlsx", "QA3");
        return bookitFile.getDataList();

    }
//    @ParameterizedTest
//    @MethodSource("getExcelData")
//    public void bookitUserTest(Map){
//
//    }


}
