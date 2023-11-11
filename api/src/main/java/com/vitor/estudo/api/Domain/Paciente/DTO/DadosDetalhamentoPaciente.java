package com.vitor.estudo.api.Domain.Paciente.DTO;

import com.vitor.estudo.api.Domain.Endereco.Endereco;
import com.vitor.estudo.api.Domain.Paciente.Paciente;

public record DadosDetalhamentoPaciente(Long id,String nome, String email, String cpf, Endereco endereco) {
    public DadosDetalhamentoPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(),paciente.getEndereco());
    }
    
}
