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
public class AddressDto {
    private String address_id;
    private String street;
    private int external_number;
    private int internal_number;
    private String colony;
    private String municipality;
    private String city;
    private int post_code;

}
