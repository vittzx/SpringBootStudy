package com.vitor.estudo.api.Medico;

public record DadosListagemMedico(Long id,String nome, String email, String crm, Especialidade especialidade) {

    public DadosListagemMedico(Medico dados){
        this(dados.getId() ,dados.getNome(),dados.getEmail(), dados.getCrm(), dados.getEspecialidade());
    }
}

// adding id 