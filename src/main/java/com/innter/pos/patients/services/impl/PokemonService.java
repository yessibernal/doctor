package com.innter.pos.patients.services.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.innter.pos.patients.DAOs.rest.PokeApi;
import com.innter.pos.patients.DTOs.PokeApiResponse;
import com.innter.pos.patients.DTOs.PokemonDto;
import com.innter.pos.patients.services.IPokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonService implements IPokemonService {

    @Autowired
    PokeApi pokeApi;


    @Override
    public PokeApiResponse getListPokemons(Integer limit, Integer offset) {
        Gson gson = new Gson();
        PokeApiResponse pokeApiResponse = gson.fromJson(pokeApi.getListOfPokemons(limit, offset), PokeApiResponse.class); //Se iguala a un objeto existente de tipo PokeApiResponse y se llena ese objeto con la api existente de pokemons
        for (PokemonDto pokemonDto : pokeApiResponse.getResults()) {//Pon tu lista aquí y crea un objeto para cada recorrido
            String pokemonSelected = pokeApi.getImagePokemon(pokemonDto.getUrl());//string que se iguala a un método que se manda a llamar de poke api donde se pide
            JsonObject jsonObject = new Gson().fromJson(pokemonSelected, JsonObject.class);//se convierte el string en gson objet con gson para manipularlo
            jsonObject.isJsonObject();
            String image = jsonObject.get("sprites").getAsJsonObject().get("other").getAsJsonObject().get("home").getAsJsonObject().get("front_default").getAsString();//se coloca cada valor de la lista hasta acceder a la url de donde está la imagen, se va convirtiendo a un objeto y otra vez a se coloca el get al último donde está la imagen se pone el as string y se iguala a un string
            pokemonDto.setImage(image);//Se setea la url a cada valor de image en el objeto de PokemonDto
        }
        String next = pokeApiResponse.getNext();// Se obtiene el parametro de la lista results y se iguala a un string
        String[] parts = next.split("\\?");//Con el split se separa el string en las partes necesarias y con esta parte ("\\?") se sabe   partir de queb lugar se va a separar
        String part1 = parts[0]; // https://pokeapi.co/api/v2/pokemon?
        String part2 = parts[1]; // offset=0&limit=20
        pokeApiResponse.setNext(part1.replace("https://pokeapi.co/api/v2/pokemon", "http://localhost:8000/api/pokemon/list" + "?" + part2));//Una vez con las partes separadas se remplaza por la url local
        String previous = pokeApiResponse.getPrevious();
        String[] partsPrevious = previous.split("\\?");
        String partsPrevious1 = partsPrevious[0];
        String partsPrevious2 = partsPrevious[1];
        pokeApiResponse.setPrevious(partsPrevious1.replace("https://pokeapi.co/api/v2/pokemon", "http://localhost:8000/api/pokemon/list" + "?" + partsPrevious2));
        return pokeApiResponse;
    }

}
