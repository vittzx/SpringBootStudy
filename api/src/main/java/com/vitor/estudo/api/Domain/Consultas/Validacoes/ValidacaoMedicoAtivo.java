package com.vitor.estudo.api.Domain.Consultas.Validacoes;

import com.vitor.estudo.api.Domain.Medico.Repository.MedicoRepository;

import jakarta.validation.ValidationException;

public class ValidacaoMedicoAtivo {
    private MedicoRepository medicoRepository;

    public void validar(Long idMedico){

        // idMedico opcional 
        if(idMedico == null){
            return;
        }

        Boolean medicoExiste = medicoRepository.findAtivoById(idMedico);
        if(!medicoExiste){
            throw new ValidationException("NAO FOI POSSIVEL ESCOLHER O MEDICO: ID do medico invalido ou inexistente!");
        }
    }
}
