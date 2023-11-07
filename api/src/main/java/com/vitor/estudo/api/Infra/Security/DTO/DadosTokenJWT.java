package com.vitor.estudo.api.Infra.Security.DTO;

import jakarta.validation.constraints.NotBlank;

public record DadosTokenJWT(@NotBlank String token) {
    public DadosTokenJWT(String token){
        this.token = token;
    }
}
