package com.vitor.estudo.api.Domain.Consultas.DTO;

import java.time.LocalDateTime;


// dados vao para o banco de dados e vao ser retornados ao cliente.
public record DadosDetalhamentoConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime dataConsulta) {

    
}
