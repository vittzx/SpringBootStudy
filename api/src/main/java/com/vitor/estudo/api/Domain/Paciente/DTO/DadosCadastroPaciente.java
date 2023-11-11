package com.vitor.estudo.api.Domain.Paciente.DTO;

import com.vitor.estudo.api.Domain.Endereco.DTO.DadosEndereco;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroPaciente(
    
    @NotBlank
    String nome, 
    @NotBlank
    String email, 
    
    @NotBlank @Pattern(regexp="\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
    String cpf, 
    
    @NotNull @Valid
    DadosEndereco endereco){
    
}
