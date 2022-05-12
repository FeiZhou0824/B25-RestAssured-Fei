package com.cydeo.day11;

import org.junit.jupiter.api.*;

public class TestLifeCycleAnnotations {

    //beforeClass is testNG version of beforeAll, same logic
    @BeforeAll
    public static void init(){
        System.out.println("before all is running");
    }

    //beforeMethod is testNG version of beforeEach, same logic
    @BeforeEach
    public void initEach(){
        System.out.println("\t Before each is running");
    }

    @Test
    public void test1(){
        System.out.println("Test 1 is running");
    }

    @AfterEach
    public void closeEach(){
        System.out.println("\t After each is close");
    }


    @Disabled // this is ignore the test case
    @Test
    public void test2(){
        System.out.println("Test 2 is running");
    }

    @AfterAll
    public static void close(){
        System.out.println("After all is running");
    }


}
