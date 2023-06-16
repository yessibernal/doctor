package com.innter.pos.patients.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class InternalServerErrorInnter extends RuntimeException {

    private String code;
    private HttpStatus status;

    public InternalServerErrorInnter(String code, HttpStatus status, String message) {

        super(message);
        this.code = code;
        this.status = status;
    }
}
