package com.cydeo.day11;

import io.restassured.path.json.JsonPath;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class CsvSourceParameterizedTest {


    // Test first number + second number = third number   Csv, comma,separate,value
//            1, 3 , 4
//            2, 3 , 5
//            8, 7 , 15
//            8, 7 , 15

    @ParameterizedTest
    @CsvSource({" 1, 3 , 4",
                 "2, 3 , 5",
                 "8, 7 , 15",
                 " 10, 9 , 19"})
    public void testAddition(int num1, int num2, int sum){
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("sum = " + sum);
        MatcherAssert.assertThat(num1+num2, Matchers.equalTo(sum));

    }

    // Write a parameterized test for this request
    // GET https://api.zippopotam.us/us/{state}/{city}
    /*
        "NY, New York",
        "CO, Denver",
        "VA, Fairfax",
        "VA, Arlington",
        "MA, Boston",
        "NY, New York",
        "MD, Annapolis"
     */
    //verify place name contains your city name
    //print number of places for each request

    @ParameterizedTest
    @CsvSource({" NY,New York",
            "CO, Denver",
            "VA, Fairfax",
            " VA, Arlington",
             "MA, Boston",
             "MD, Annapolis"})
    public void testCity(String state, String city){

        System.out.println("state = " + state);
        System.out.println("city = " + city);

//      JsonPath jsonPath =  given()
//                .pathParam("state",state)
//                .and()
//                .pathParam("city",city)
//                .when()
//                .get("https://api.zippopotam.us/us/{state}/{city}")
//                .then()
//                .statusCode(200).extract().jsonPath();
//      jsonPath.param("country abbreviation.places.place name",hasValue(city));
//      int placeNum = jsonPath.getList("places").size();
//        System.out.println("placeNum = " + placeNum);

     int placesNumber =    given()
                .pathParam("state",state)
                .and()
                .pathParam("city",city)
                .when()
                .get("https://api.zippopotam.us/us/{state}/{city}")
                .then()
                .statusCode(200)
                .body("places.'place name'",everyItem(containsStringIgnoringCase(city)))
                .extract().jsonPath().getList("places").size();
        System.out.println("placesNumber = " + placesNumber);


    }

}
