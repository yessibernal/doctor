package com.innter.pos.patients.DTOs;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {

    @NotNull
    @JsonProperty("file_number")
    private String fileNumber;

    @NotNull
    @NotBlank
    @JsonProperty("diagnostic")
    private String diagnostic;

    @NotNull
    @JsonProperty("persona")
    private PersonDto personDto;
}
