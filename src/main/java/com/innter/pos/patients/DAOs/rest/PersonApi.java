package com.innter.pos.patients.DAOs.rest;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class PersonApi {

    @Autowired
    OkHttpClient okHttpClient;

    public String createPerson(String personAddress) {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType, personAddress);
        Request request = new Request.Builder()
                .url("http://localhost:8081/api/person")
                .method("POST", requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String persona = response.body().string();
            return persona;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPerson(int id) {
        Request request = new Request.Builder()
                .url("http://localhost:8081/api/person/" + id)
                .method("GET", null)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String personaId = response.body().string();
            return personaId;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String updatePerson(String personBody, int id) {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType, personBody);
        Request request = new Request.Builder()
                .url("http://localhost:8081/api/person/" + id)
                .method("PUT", requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String persona = response.body().string();
            return persona;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String deletePerson(int id) {
        Request request = new Request.Builder()
                .url("http://localhost:8081/api/person/" + id)
                .method("DELETE", null)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String personaId = response.body().string();
            return personaId;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
