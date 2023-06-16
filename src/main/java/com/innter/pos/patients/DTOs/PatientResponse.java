package com.innter.pos.patients.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponse {

    @NotNull
    @JsonProperty("patient_id")
    private Long id;

    @NotNull
    @JsonProperty("file_number")
    private String fileNumber;

    @NotNull
    @JsonProperty("diagnostic")
    private String diagnostic;


    @NotNull
    @JsonProperty("person")
    private PersonDto personDto;

    @NotNull
    @JsonProperty("doctor")
    private DoctorDto doctorDto;

}
