package com.vitor.estudo.api.Domain.Consultas.DTO;

import java.time.LocalDateTime;

import com.vitor.estudo.api.Domain.Consultas.Consulta;


// dados vao para o banco de dados e vao ser retornados ao cliente.
public record DadosDetalhamentoConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime dataConsulta) {

    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }

    
}
