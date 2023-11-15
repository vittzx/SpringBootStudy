package com.vitor.estudo.api.Domain.Consultas.Validacoes;

import java.time.Duration;
import java.time.LocalDateTime;

import jakarta.validation.ValidationException;

public class ValidadorHorarioAntecedencia {
    public void validar(LocalDateTime dataConsulta){
        LocalDateTime agora = LocalDateTime.now();
        Long diferncaTempo = Duration.between(agora, dataConsulta).toMinutes();

        if(30 > diferncaTempo){
            throw new ValidationException("Consultas devem ser agendandas com antecedência minima de 30 minutos");
        } 


    }
}
