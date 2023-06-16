package com.innter.pos.patients.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorEditedRequest {

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
