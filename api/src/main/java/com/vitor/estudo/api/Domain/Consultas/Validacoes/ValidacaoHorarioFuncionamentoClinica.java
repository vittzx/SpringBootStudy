package com.vitor.estudo.api.Domain.Consultas.Validacoes;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import com.vitor.estudo.api.Domain.Consultas.Service.ValidacaoExecption;

public class ValidacaoHorarioFuncionamentoClinica {
    
    public void validar(LocalDateTime dataConsulta){
        boolean domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean aberturaClinica = dataConsulta.getHour() < 7;
        boolean fechamentoClinica = dataConsulta.getHour() > 18; // fecha as 19, porem como eh uma hora de consulta nao se pode marcar as 19, pois eh o fechamento da clinica.
        if(domingo || aberturaClinica || fechamentoClinica) throw new ValidacaoExecption("Consulta fora de horario");
    }

}
