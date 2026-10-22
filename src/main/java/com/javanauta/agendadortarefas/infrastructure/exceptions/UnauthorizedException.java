package com.javanauta.agendadortarefas.infrastructure.exceptions;


import org.springframework.security.core.AuthenticationException;

/**
 * Exceção utilizada quando ocorre uma falha durante a autenticação do usuário.
 * Herda de AuthenticationException para representar erros relacionados à autenticação.
 */
public class UnauthorizedException extends AuthenticationException {

    public UnauthorizedException(String mensagem){
        super(mensagem);
    }

    public UnauthorizedException(String mensagem , Throwable trowable){
        super(mensagem,trowable);
    }


}
