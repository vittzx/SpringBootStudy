package com.vitor.estudo.api.Domain.Usuario.DTO;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacaoUsuario( @NotBlank String login, @NotBlank String senha) {
    
}
