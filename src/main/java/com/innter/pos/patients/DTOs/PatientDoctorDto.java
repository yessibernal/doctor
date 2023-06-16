package com.innter.pos.patients.DTOs;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDoctorDto {
    private String patient_id;
    private String file_number;
    private String diagnostic;
    private PersonDto person;
    private DoctorDto doctor;
}
