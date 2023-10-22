package com.vitor.estudo.api.Domain.Medico.DTO;

import com.vitor.estudo.api.Domain.Endereco.Endereco;
import com.vitor.estudo.api.Domain.Medico.Especialidade;
import com.vitor.estudo.api.Domain.Medico.Medico;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, Especialidade especialidade, Endereco endereco) {
    public DadosDetalhamentoMedico(Medico medico){ 
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
