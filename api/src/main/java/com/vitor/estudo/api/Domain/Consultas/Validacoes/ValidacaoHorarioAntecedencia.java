package com.vitor.estudo.api.Domain.Consultas.Validacoes;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.vitor.estudo.api.Domain.Consultas.DTO.DadosAgendamentoConsulta;

import jakarta.validation.ValidationException;

@Component
public class ValidacaoHorarioAntecedencia implements ValidadorAgendamentoConsulta {
    public void validar(DadosAgendamentoConsulta dados){
        LocalDateTime agora = LocalDateTime.now();
        Long diferncaTempo = Duration.between(agora, dados.dataConsulta()).toMinutes();

        if(30 > diferncaTempo){
            throw new ValidationException("Consultas devem ser agendandas com antecedência minima de 30 minutos");
        } 

        
    }
}
