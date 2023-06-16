package com.innter.pos.patients.utils;

import com.innter.pos.patients.exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = BadRequestInnter.class)//Error 400
    public ResponseEntity<ErrorDto> badRequestInnter(BadRequestInnter er) {
        ErrorDto error = ErrorDto.builder().code(er.getCode()).message(er.getMessage()).build();
        return new ResponseEntity<>(error, er.getStatus());
    }

    @ExceptionHandler(value = {NotFoundInnter.class, NoSuchFileExceptionInnter.class})//Error 404
    public ResponseEntity<ErrorDto> notFoundInnter(NotFoundInnter er) {
        ErrorDto error = ErrorDto.builder().code(er.getCode()).message(er.getMessage()).build();
        return new ResponseEntity<>(error, er.getStatus());
    }

    @ExceptionHandler(value = InternalServerErrorInnter.class)//Error 500
    public ResponseEntity<ErrorDto> InternalServerErrorInnter(InternalServerErrorInnter er) {
        ErrorDto error = ErrorDto.builder().code(er.getCode()).message(er.getMessage()).build();
        return new ResponseEntity<>(error, er.getStatus());
    }
}
