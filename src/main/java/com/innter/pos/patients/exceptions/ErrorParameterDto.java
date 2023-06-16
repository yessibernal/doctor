package com.innter.pos.patients.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorParameterDto {
    private String code;
    private String codeTwo;
    private String message;
    private String messageTwo;
}
