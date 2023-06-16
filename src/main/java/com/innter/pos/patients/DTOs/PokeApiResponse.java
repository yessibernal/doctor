package com.innter.pos.patients.DTOs;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class PokeApiResponse {
    private Integer count;
    private String next;
    private String previous;
    private List<PokemonDto> results;
}
