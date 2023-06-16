package com.innter.pos.patients.DTOs;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {

    private Long id;
    private String name;
    private String last_name;
    private String surname;
    private String cedula;
    private String phone;
    private String email;
    private String specialty;

}
