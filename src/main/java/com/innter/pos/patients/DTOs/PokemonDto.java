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

public class PokemonDto {
    private String name;
    private String url;
    private String image;
}
