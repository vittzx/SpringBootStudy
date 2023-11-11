package com.vitor.estudo.api.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitor.estudo.api.Domain.Paciente.Paciente;
import com.vitor.estudo.api.Domain.Paciente.Repository.PacienteRepository;


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



}
