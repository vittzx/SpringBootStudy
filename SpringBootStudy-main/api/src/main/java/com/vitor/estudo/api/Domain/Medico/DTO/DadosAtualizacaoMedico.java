package com.vitor.estudo.api.Domain.Medico.DTO;

import com.vitor.estudo.api.Domain.Endereco.DTO.DadosEndereco;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
    @NotNull Long id, 
    String nome, 
    DadosEndereco Endereco) {
    
}
