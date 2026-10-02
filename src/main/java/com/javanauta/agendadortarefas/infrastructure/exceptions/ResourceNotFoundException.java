package com.javanauta.agendadortarefas.infrastructure.exceptions;

// Exceção usada quando um recurso não é encontrado.
public class ResourceNotFoundException extends RuntimeException {

    // Cria a exceção com uma mensagem personalizada.
    public ResourceNotFoundException(String mensagem) {
        super(mensagem);
    }

    // Cria a exceção com mensagem e a causa original do erro.
    public ResourceNotFoundException(String mensagem, Throwable throwable) {
        super(mensagem, throwable);
    }
}

