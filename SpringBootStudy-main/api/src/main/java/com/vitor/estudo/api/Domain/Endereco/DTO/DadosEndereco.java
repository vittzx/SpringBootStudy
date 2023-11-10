package com.vitor.estudo.api.Domain.Endereco.DTO;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
    @NotBlank
    String logradouro,
    @NotBlank 
    String bairro,
    @NotBlank
    @Pattern(regexp="\\d{8}") 
    String cep, 
    String cidade, 
    String uf, 
    String numero, 
    String complemento) {
    
}
