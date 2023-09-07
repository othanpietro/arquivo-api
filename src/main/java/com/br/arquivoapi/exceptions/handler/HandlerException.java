package com.br.arquivoapi.exceptions.handler;


import com.br.arquivoapi.exceptions.ArquivoException;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException {


    @ExceptionHandler(ArquivoException.class)
    public ResponseEntity<ErrorMessage> catchHttpMessageNotReadableException(ArquivoException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(e.getMessage()));
    }

}
