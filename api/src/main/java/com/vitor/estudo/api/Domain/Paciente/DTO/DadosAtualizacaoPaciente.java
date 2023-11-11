package com.vitor.estudo.api.Domain.Paciente.DTO;


import com.vitor.estudo.api.Domain.Endereco.DTO.DadosEndereco;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(@NotNull Long id, String nome ,String email, DadosEndereco endereco) {
    
}
