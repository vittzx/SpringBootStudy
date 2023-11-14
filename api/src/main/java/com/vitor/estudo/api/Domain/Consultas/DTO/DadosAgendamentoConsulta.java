package com.vitor.estudo.api.Domain.Consultas.DTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vitor.estudo.api.Domain.Medico.Especialidade;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

// Dados que o usuario tras para o servidor.,

public record DadosAgendamentoConsulta(
    Long idMedico , 
    
    @NotNull
    Long idPaciente, 
    
    @NotNull
    @Future // significa q a data tem que ser no futuro.
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    LocalDateTime dataConsulta,

    Especialidade especialidade)
    
    {
}
