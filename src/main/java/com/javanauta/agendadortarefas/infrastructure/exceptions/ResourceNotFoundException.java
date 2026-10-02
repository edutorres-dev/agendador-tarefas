package com.javanauta.agendadortarefas.infrastructure.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensagem){
        super(mensagem);
    }

    public ResourceNotFoundException(String mensagem , Throwable trowable){
        super(mensagem,trowable);
    }


}
