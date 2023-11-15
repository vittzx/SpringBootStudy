package com.vitor.estudo.api.Domain.Consultas.Validacoes;

import java.time.LocalDateTime;

import com.vitor.estudo.api.Domain.Consultas.Repository.ConsultasRepository;

import jakarta.validation.ValidationException;

public class ValidacaoMedicoComConsultaMesmoHorario {
    
    private ConsultasRepository consultasRepository;

    public void valdar(Long idMedico,LocalDateTime dataConsulta){
        Boolean verificarConsultaMedicoMesmoHorario = consultasRepository.existsByMedicoIdAndData(idMedico,dataConsulta); // se existe uma consulta com este medico e esta data 
        
        if(verificarConsultaMedicoMesmoHorario) throw new ValidationException("Este medico ja possui uma consulta neste horario.");
    }

}
