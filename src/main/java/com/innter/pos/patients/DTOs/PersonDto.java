package com.innter.pos.patients.DTOs;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PersonDto {

    private int person_id;
    private String name;
    private String last_name;
    private String surname;
    private String gender;
    private String birth_date;
    private String email;
    private String cellphone;
    private String phone;
    private AddressDto address;
    private PatientDto patient;
}
