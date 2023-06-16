package com.innter.pos.patients.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestRequest {

    @NotNull
    @JsonProperty("display_id")
    private String displayId;

    @NotNull
    @JsonProperty("test_name")
    private String testName;

    @NotNull
    @JsonProperty("report_name")
    private String reportName;

    @NotNull
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

    @NotNull
    @JsonProperty("lower_limit")
    private String lowerLimit;

    @NotNull
    @JsonProperty("upper_limit")
    private String upperLimit;

    @NotNull
    @JsonProperty("sat_key")
    private String satKey;

    @NotNull
    @JsonProperty("sat_catalog")
    private String satCatalog;

    @NotNull
    @Valid
    @JsonProperty("test_instruction")
    private TestInstructionRequest testInstruction;

}
