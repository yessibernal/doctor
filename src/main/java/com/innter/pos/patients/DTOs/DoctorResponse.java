package com.innter.pos.patients.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {

    @NotNull
    @JsonProperty("doctor_id")
    private Long id;

    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("last_name")
    private String lastName;

    @NotNull
    @JsonProperty("surname")
    private String surname;

    @NotNull
    @JsonProperty("cedula")
    @Size(min = 7, max = 8)
    private String cedula;

    @NotNull
    @JsonProperty("phone")
    @Size(min = 10, max = 13)
    private String phone;

    @NotNull
    @JsonProperty("email")
    @Email
    private String email;

    @NotNull
    @JsonProperty("specialty")
    private String specialty;
}
