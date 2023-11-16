package com.vitor.estudo.api.Domain.Consultas.Validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vitor.estudo.api.Domain.Consultas.DTO.DadosAgendamentoConsulta;
import com.vitor.estudo.api.Domain.Paciente.Repository.PacienteRepository;

import jakarta.validation.ValidationException;

@Component
public class ValidacaoPacienteAtivo implements ValidadorAgendamentoConsulta {
    
    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados){
        if(!pacienteRepository.findAtivoById(dados.idPaciente())){
            throw new ValidationException("Pacietnte nao esta ativo no sistema");
        }
    }
}
