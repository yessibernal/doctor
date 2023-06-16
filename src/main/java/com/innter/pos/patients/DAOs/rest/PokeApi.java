package com.innter.pos.patients.DAOs.rest;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class PokeApi {

    @Autowired
    OkHttpClient okHttpClient;

    public String getListOfPokemons(Integer limit,Integer offset) {
        Request request = new Request.Builder()
                .url("https://pokeapi.co/api/v2/pokemon?limit="+ limit +"&offset=" +offset)
                .method("GET", null)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String personaId = response.body().string();
            System.out.println(personaId);
            return personaId;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getImagePokemon(String url) {
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
