package com.vitor.estudo.api.Domain.Consultas.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitor.estudo.api.Domain.Consultas.Consulta;
import com.vitor.estudo.api.Domain.Consultas.DTO.DadosAgendamentoConsulta;
import com.vitor.estudo.api.Domain.Consultas.DTO.DadosCancelamentoConsulta;
import com.vitor.estudo.api.Domain.Consultas.DTO.DadosDetalhamentoConsulta;
import com.vitor.estudo.api.Domain.Consultas.Repository.ConsultasRepository;
import com.vitor.estudo.api.Domain.Consultas.Validacoes.ValidadorAgendamentoConsulta;
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


    @Autowired
    private List<ValidadorAgendamentoConsulta> validadores;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){

        if(!pacienteRepository.existsById(dados.idPaciente()))
        {
            throw new ValidacaoExecption("Id do paciente inexistente!");
        }


        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico()))
        {
            throw new ValidacaoExecption("Id do medico inexistente!");
        }

        // validacoes da regra de negocio vao ser criadas em outra classe | https://trello.com/c/BmaWcJot/9-agendamento-de-consultas
        validadores.forEach(v -> v.validar(dados));

        Medico medico = escolherMedico(dados);
        if(medico == null){
            throw new ValidacaoExecption("Nao ha medico disponivel nessa data.");
        }
        Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        // Paciente paciente2 = pacienteRepository.findById(dados.idPaciente()).get();
        
        Consulta consulta = new Consulta(null, medico, paciente, dados.dataConsulta(),null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
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


    public void cancelar(DadosCancelamentoConsulta dados){
        Consulta consultaCancelar = null;
        if(consultaRepository.existsById(dados.idConsulta())){
            consultaCancelar = consultaRepository.getReferenceById(dados.idConsulta());
        }
        else{
            throw new ValidacaoExecption("ID da consulta nao existe, ou esta invaldo"); 
        }   

        consultaCancelar.cancelar(dados.motivoCancelamento());
    }

}
