package com.vitor.estudo.api.Medico.DTO;

import com.vitor.estudo.api.Endereco.Endereco;
import com.vitor.estudo.api.Medico.Especialidade;
import com.vitor.estudo.api.Medico.Medico;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, Especialidade especialidade, Endereco endereco) {
    public DadosDetalhamentoMedico(Medico medico){ 
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
