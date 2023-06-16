package com.innter.pos.patients.services;


import com.innter.pos.patients.DTOs.PokeApiResponse;

public interface IPokemonService {

    public PokeApiResponse getListPokemons(Integer limit,Integer offset);

}
