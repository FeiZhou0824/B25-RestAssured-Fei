package com.cydeo.POJO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {


    @JsonProperty("first_nme")
    private String firstName;

    @JsonProperty("last_name")
    private  String lastName;

    @JsonProperty("job_id")
    private  String jobId;
    private  int salary;
}
