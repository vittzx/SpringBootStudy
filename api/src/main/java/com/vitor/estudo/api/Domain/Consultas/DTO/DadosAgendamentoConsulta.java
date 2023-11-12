package com.vitor.estudo.api.Domain.Consultas.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

// Dados que o usuario tras para o servidor.,

public record DadosAgendamentoConsulta(
    Long idMedico , 
    
    @NotNull
    Long idPaciente, 
    
    @NotNull
    @Future // significa q a data tem que ser no futuro.
    LocalDateTime dataConsulta) {
    
}
