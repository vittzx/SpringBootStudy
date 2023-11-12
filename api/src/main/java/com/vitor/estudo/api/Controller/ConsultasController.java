package com.vitor.estudo.api.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitor.estudo.api.Domain.Consultas.DTO.DadosAgendamentoConsulta;
import com.vitor.estudo.api.Domain.Consultas.DTO.DadosDetalhamentoConsulta;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/consultas")
public class ConsultasController {
    
    @PostMapping
    public ResponseEntity<DadosDetalhamentoConsulta> cadastrarConsulta(@RequestBody @Valid DadosAgendamentoConsulta dados){
        System.out.println(dados);


        return ResponseEntity.ok(new DadosDetalhamentoConsulta(null,null, null ,null));
        
    }
}
