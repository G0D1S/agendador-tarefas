package com.igor.agendadortarefa.infrastructure.exeception;

public class ResourceNotFoundExeception extends RuntimeException{

    public ResourceNotFoundExeception (String mensagem){
        super(mensagem);
    }

    public ResourceNotFoundExeception (String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }

}
