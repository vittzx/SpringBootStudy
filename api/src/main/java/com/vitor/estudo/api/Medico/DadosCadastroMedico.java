package com.vitor.estudo.api.Medico;

import com.vitor.estudo.api.Endereco.DadosEndereco;

public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco) {
    
}
