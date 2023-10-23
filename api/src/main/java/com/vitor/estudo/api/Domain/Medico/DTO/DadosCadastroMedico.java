package com.vitor.estudo.api.Domain.Medico.DTO;

import com.vitor.estudo.api.Domain.Endereco.DadosEndereco;
import com.vitor.estudo.api.Domain.Medico.Especialidade;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(
    // @NotBlank(message="Campo nome é obrigatorio")    // nao eh nulo nem vazio, faz essa validacao
    @NotBlank(message="{nome.obrigatorio}")
    String nome, 
    
    // @NotBlank
    // @Email(message="Campo email é obrigatorio") // validacao de email
    @NotBlank(message="{email.obrigatorio}")
    @Email(message="{email.invalido}")
    String email, 
    
    // @NotBlank(message="Campo CRM é obrigatorio")
    // @Pattern(regexp = "\\d{4,6}", message="Precisa ser entre 4-6 digitos") // expressao regular \\ d de 4 a 6 digitos 
    @NotBlank(message="{crm.obrigatorio}")
    @Pattern(regexp="\\d{4,6}", message="{crm.invalido}")
    String crm, 
    
    // @NotNull(message="Campo especialidade é obrigatório e precisa estar dentro dos padroes.") // nao eh not blank pois nao eh String 
    @NotNull(message="{especialidade.obrigatorio}")
    Especialidade especialidade, 
    
    // @NotNull(message="Campo endereco é obrigatorio")
    @NotNull(message="{endereco.obrigatorio}")
    @Valid
    DadosEndereco endereco) {
    
}
