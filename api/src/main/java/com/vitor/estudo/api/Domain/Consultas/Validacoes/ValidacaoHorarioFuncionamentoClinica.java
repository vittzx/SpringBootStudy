package com.vitor.estudo.api.Domain.Consultas.Validacoes;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import com.vitor.estudo.api.Domain.Consultas.DTO.DadosAgendamentoConsulta;
import com.vitor.estudo.api.Domain.Consultas.Service.ValidacaoExecption;

@Component
public class ValidacaoHorarioFuncionamentoClinica implements ValidadorAgendamentoConsulta {
    
    public void validar(DadosAgendamentoConsulta dados){
        boolean domingo = dados.dataConsulta().getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean aberturaClinica = dados.dataConsulta().getHour() < 7;
        boolean fechamentoClinica = dados.dataConsulta().getHour() > 18; // fecha as 19, porem como eh uma hora de consulta nao se pode marcar as 19, pois eh o fechamento da clinica.
        if(aberturaClinica || fechamentoClinica) throw new ValidacaoExecption("Consulta fora de horario: Agendar entre as 07:00 ate 18:00");
        if(domingo){
            throw new ValidacaoExecption("Cliníca não abre as domingos, somente de segunda à sabado.");
        }
    }

}
