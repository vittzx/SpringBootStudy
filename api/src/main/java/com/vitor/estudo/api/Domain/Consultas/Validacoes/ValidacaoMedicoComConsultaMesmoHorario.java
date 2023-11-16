package com.vitor.estudo.api.Domain.Consultas.Validacoes;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vitor.estudo.api.Domain.Consultas.DTO.DadosAgendamentoConsulta;
import com.vitor.estudo.api.Domain.Consultas.Repository.ConsultasRepository;

import jakarta.validation.ValidationException;

@Component
public class ValidacaoMedicoComConsultaMesmoHorario implements ValidadorAgendamentoConsulta {
    
    @Autowired
    private ConsultasRepository consultasRepository;

    public void validar(DadosAgendamentoConsulta dados){
        Boolean verificarConsultaMedicoMesmoHorario = consultasRepository.existsByMedicoIdAndData(dados.idMedico(),dados.dataConsulta()); // se existe uma consulta com este medico e esta data 
        
        if(verificarConsultaMedicoMesmoHorario) throw new ValidationException("Este medico ja possui uma consulta neste horario.");
    }

}
