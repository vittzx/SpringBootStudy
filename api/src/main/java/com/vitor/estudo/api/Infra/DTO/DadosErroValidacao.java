package com.vitor.estudo.api.Infra.DTO;

import org.springframework.validation.FieldError;

public record DadosErroValidacao(String field, String defaultMessage) {
    public DadosErroValidacao(FieldError e){
        this(e.getField(), e.getDefaultMessage());
    }
}
