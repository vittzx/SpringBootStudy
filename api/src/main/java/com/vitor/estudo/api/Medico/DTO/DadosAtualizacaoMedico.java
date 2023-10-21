package com.vitor.estudo.api.Medico.DTO;

import com.vitor.estudo.api.Endereco.DadosEndereco;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
    @NotNull Long id, 
    String nome, 
    DadosEndereco Endereco) {
    
}
