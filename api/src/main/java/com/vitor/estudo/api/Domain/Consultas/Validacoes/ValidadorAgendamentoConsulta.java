package com.vitor.estudo.api.Domain.Consultas.Validacoes;

import com.vitor.estudo.api.Domain.Consultas.DTO.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoConsulta {
    void validar(DadosAgendamentoConsulta dados);
}
