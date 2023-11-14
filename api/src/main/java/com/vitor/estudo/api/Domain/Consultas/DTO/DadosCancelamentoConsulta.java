package com.vitor.estudo.api.Domain.Consultas.DTO;

import com.vitor.estudo.api.Domain.Consultas.MotivoCancelamento;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(
        @NotNull Long idConsulta,


        @NotNull    
        MotivoCancelamento motivoCancelamento
        ) {
    
}
