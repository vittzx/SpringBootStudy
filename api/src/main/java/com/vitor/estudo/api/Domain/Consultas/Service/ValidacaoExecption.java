package com.vitor.estudo.api.Domain.Consultas.Service;

public class ValidacaoExecption extends RuntimeException {
 
    public ValidacaoExecption(String mensagem){
        super(mensagem);
    }
}
