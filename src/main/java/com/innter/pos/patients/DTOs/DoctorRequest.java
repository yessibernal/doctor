package com.innter.pos.patients.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
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
public class DoctorRequest {

    @NotNull
    @NotBlank
    @JsonProperty("name")
    private String name;

    @NotNull
    @NotBlank
    @JsonProperty("last_name")
    private String lastName;

    @NotNull
    @NotBlank
    @JsonProperty("surname")
    private String surname;

    @NotNull
    @NotBlank
    @JsonProperty("cedula")
    @Size(min = 7, max = 8)
    private String cedula;

    @NotNull
    @NotBlank
    @JsonProperty("phone")
    @Size(min = 10, max = 13)
    private String phone;

    @NotNull
    @NotBlank
    @JsonProperty("email")
    @Email
    private String email;

    @NotNull
    @NotBlank
    @JsonProperty("specialty")
    private String specialty;
}
