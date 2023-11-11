package com.vitor.estudo.api.Domain.Paciente.DTO;

import com.vitor.estudo.api.Domain.Paciente.Paciente;

public record DadosListagemPaciente(Long id, String nome, String email, String cpf ) {
    public DadosListagemPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
