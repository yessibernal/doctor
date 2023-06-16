package com.innter.pos.patients.DTOs;
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
public class PatientDto {
    private String patient_id;
    private String file_number;
    private String diagnostic;
    private PersonDto person;
}
