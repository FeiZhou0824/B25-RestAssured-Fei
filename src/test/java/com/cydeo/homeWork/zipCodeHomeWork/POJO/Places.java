package com.cydeo.homeWork.zipCodeHomeWork.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class Places {

    @JsonProperty("place name")
    private String placeName;
    private String state;
    private double latitude;

}
