package com.vitor.estudo.api.Medico.DTO;

import com.vitor.estudo.api.Medico.Especialidade;
import com.vitor.estudo.api.Medico.Medico;

public record DadosListagemMedico(Long id,String nome, String email, String crm, Especialidade especialidade) {

    public DadosListagemMedico(Medico dados){
        this(dados.getId() ,dados.getNome(),dados.getEmail(), dados.getCrm(), dados.getEspecialidade());
    }
}

// adding id 