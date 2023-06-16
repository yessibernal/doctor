package com.innter.pos.patients.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TestResponse {

    @JsonProperty("test_id")
    private Long id;

    @JsonProperty("display_id")
    private String displayId;

    @JsonProperty("test_name")
    private String testName;

    @JsonProperty("report_name")
    private String reportName;

    @JsonProperty("delivery_time")
    private String deliveryTime;

    @JsonProperty("department")
    private int department;

    @JsonProperty("sample_type")
    private int sampleType;

    @JsonProperty("container_type")
    private int containerType;

    @JsonProperty("result")
    private int result;

    @JsonProperty("units")
    private int units;

    @JsonProperty("decimals")
    private int decimals;

    @JsonProperty("default_value")
    private String defaultValue;

    @JsonProperty("technique_used")
    private int techniqueUsed;

    @JsonProperty("maker")
    private int maker;

    @JsonProperty("lower_limit")
    private String lowerLimit;

    @JsonProperty("upper_limit")
    private String upperLimit;

    @JsonProperty("sat_key")
    private String satKey;

    @JsonProperty("sat_catalog")
    private String satCatalog;

}
