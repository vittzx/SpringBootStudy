package com.vitor.estudo.api.Controller;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import com.vitor.estudo.api.Domain.Paciente.Paciente;
import com.vitor.estudo.api.Domain.Paciente.DTO.DadosCadastroPaciente;
import com.vitor.estudo.api.Domain.Paciente.DTO.DadosDetalhamentoPaciente;
import com.vitor.estudo.api.Domain.Paciente.DTO.DadosListagemPaciente;
import com.vitor.estudo.api.Domain.Paciente.Repository.PacienteRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    PacienteRepository repository;


    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteId(@PathVariable long id){
        Paciente paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(paciente); 
    }


    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> getPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        Page<DadosListagemPaciente> page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);

        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPaciente> cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder){
        Paciente paciente = new Paciente(dados);
        repository.save(paciente);

        URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return  ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

}
