package com.br.arquivoapi.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ArquivoException extends BaseException {

    private final String message;
    public ArquivoException(String message) {
        super(HttpStatus.BAD_REQUEST);
        this.message = message;
    }
}
