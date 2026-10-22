package com.javanauta.agendadortarefas.infrastructure.exceptions;

import com.javanauta.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Centraliza o tratamento das exceções lançadas pelos Controllers da aplicação.
 * Converte as exceções em respostas HTTP com seus respectivos status.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //retorna HTTP 404 com a mensagem da exceção
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    //retorna HTTP 401 com a mensagem da exceção
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }



}
