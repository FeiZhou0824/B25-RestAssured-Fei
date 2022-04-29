package com.cydeo.homeWork.zipCodeHomeWork.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZipCode {

    @JsonProperty("post code")
    private int postCode;
    private String country;
    @JsonProperty("country abbreviation")
    private  String countryAbbreviation;
    private List<Places> places;

}
