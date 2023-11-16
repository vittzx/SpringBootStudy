package com.vitor.estudo.api.Domain.Consultas.Validacoes;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vitor.estudo.api.Domain.Consultas.DTO.DadosAgendamentoConsulta;
import com.vitor.estudo.api.Domain.Consultas.Repository.ConsultasRepository;

import jakarta.validation.ValidationException;

@Component
public class ValidacaoPacienteComConsultaNoMesmoHorario implements ValidadorAgendamentoConsulta {
    
    @Autowired
    private ConsultasRepository consultasRepository;

    public void validar(DadosAgendamentoConsulta dados){
        LocalDateTime primeiroHorario = dados.dataConsulta().withHour(7);
        LocalDateTime ultimoHorario = dados.dataConsulta().withHour(18);

        Boolean pacientePossuiConsulta = consultasRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario,ultimoHorario);
        if(pacientePossuiConsulta){
            throw new ValidationException("Paciten ja possui uma consulta agendada nesse dia");
        }
    }

}
