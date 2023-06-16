package com.innter.pos.patients.controllers;

import com.innter.pos.patients.services.IPokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PokeController {

    @Autowired
    private IPokemonService iPokemonService;


    @GetMapping(value = "api/pokemon/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getListPokemon(@RequestParam(required = false) Integer limit,
                                            @RequestParam(required = false) Integer offset) {
        return new ResponseEntity<>(iPokemonService.getListPokemons(limit,offset), HttpStatus.OK);
    }


}
