package com.vitor.estudo.api.Domain.Consultas.Validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vitor.estudo.api.Domain.Consultas.DTO.DadosAgendamentoConsulta;
import com.vitor.estudo.api.Domain.Medico.Repository.MedicoRepository;

import jakarta.validation.ValidationException;

@Component
public class ValidacaoMedicoAtivo implements ValidadorAgendamentoConsulta{

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsulta dados){

        // idMedico opcional 
        if(dados.idMedico() == null){
            return;
        }

        Boolean medicoExiste = medicoRepository.findAtivoById(dados.idMedico());
        if(!medicoExiste){
            throw new ValidationException("NAO FOI POSSIVEL ESCOLHER O MEDICO: ID do medico invalido ou inexistente!");
        }
    }
}
