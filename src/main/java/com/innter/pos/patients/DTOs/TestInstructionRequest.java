package com.innter.pos.patients.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestInstructionRequest {

    @NotNull
    @JsonProperty("patient_instructions")
    private String patientInstructions;

    @NotNull
    @JsonProperty("instructions")
    private String instructions;

    @NotNull
    @JsonProperty("exam_description")
    private String examDescription;
}
