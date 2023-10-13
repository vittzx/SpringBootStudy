package com.vitor.estudo.api.Medico;

import com.vitor.estudo.api.Endereco.DadosEndereco;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(
    @NotBlank // nao eh nulo nem vazio, faz essa validacao
    String nome, 
    
    @NotBlank
    @Email // validacao de email
    String email, 
    
    @NotBlank
    @Pattern(regexp = "\\d{4,6}") // expressao regular \\ d de 4 a 6 digitos 
    String crm, 
    
    @NotNull // nao eh not blank pois nao eh String 
    Especialidade especialidade, 
    
    @NotNull
    @Valid 
    DadosEndereco endereco) {
    
}
