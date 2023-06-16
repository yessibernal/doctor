package com.innter.pos.patients.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatalogTextRequest {
    @NotNull
    @NotBlank
    @JsonProperty("type")
    private String type;

    @NotNull
    @NotBlank
    @JsonProperty("value")
    private String value;
}
