package com.innter.pos.patients.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class NoSuchFileExceptionInnter extends NotFoundInnter { //Hereda de NotFoundInnter que es una clase personalizada, que a su vez hereda de RuntimeException
    private String code;                                       //y pide como parametros los atributos del NoSuchFileExceptionInnter asi cuando se manda a llamar,
    private HttpStatus status;                                 //implicitamente se manda a llamar el NotFoundInnter.

    public NoSuchFileExceptionInnter(String code, HttpStatus status, String message) {

        super(code, status, message);
        this.code = code;
        this.status = status;
    }
}
