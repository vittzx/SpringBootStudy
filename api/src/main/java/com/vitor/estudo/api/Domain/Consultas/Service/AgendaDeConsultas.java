package com.vitor.estudo.api.Domain.Consultas.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitor.estudo.api.Domain.Consultas.Consulta;
import com.vitor.estudo.api.Domain.Consultas.DTO.DadosAgendamentoConsulta;
import com.vitor.estudo.api.Domain.Consultas.Repository.ConsultasRepository;
import com.vitor.estudo.api.Domain.Medico.Medico;
import com.vitor.estudo.api.Domain.Medico.Repository.MedicoRepository;
import com.vitor.estudo.api.Domain.Paciente.Paciente;
import com.vitor.estudo.api.Domain.Paciente.Repository.PacienteRepository;

import jakarta.validation.ValidationException;

@Service
public class AgendaDeConsultas {
    
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultasRepository consultaRepository;


    public void agendar(DadosAgendamentoConsulta dados){

        if(!pacienteRepository.existsById(dados.idPaciente()))
        {
            throw new ValidacaoExecption("Id do paciente inexistente!");
        }


        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico()))
        {
            throw new ValidacaoExecption("Id do medico inexistente!");
        }

        Medico medico = escolherMedico(dados);
        Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        // Paciente paciente2 = pacienteRepository.findById(dados.idPaciente()).get();
        
        Consulta consulta = new Consulta(null, medico, paciente, dados.dataConsulta());
        consultaRepository.save(consulta);

    
    }


    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null){
            throw new ValidationException("Especialidade eh obrigatoria quando medicao nao for escolhido!");
        }

        return medicoRepository.esccolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.dataConsulta());
    }

}
