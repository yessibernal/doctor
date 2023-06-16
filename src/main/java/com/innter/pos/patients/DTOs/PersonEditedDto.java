package com.innter.pos.patients.DTOs;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PersonEditedDto {

    private String birth_date;
    private String email;
    private String cellphone;
    private String phone;
    private AddressEditedDto address;
}
