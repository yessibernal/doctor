package com.innter.pos.patients.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientEditedDto {

    private String diagnostic;
    private PersonEditedDto person;
}
