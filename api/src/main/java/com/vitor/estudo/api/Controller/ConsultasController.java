package com.vitor.estudo.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitor.estudo.api.Domain.Consultas.DTO.DadosAgendamentoConsulta;
import com.vitor.estudo.api.Domain.Consultas.DTO.DadosCancelamentoConsulta;
import com.vitor.estudo.api.Domain.Consultas.DTO.DadosDetalhamentoConsulta;
import com.vitor.estudo.api.Domain.Consultas.Service.AgendaDeConsultas;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultasController {
    

    @Autowired
    private AgendaDeConsultas agenda;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsulta> cadastrarConsulta(@RequestBody @Valid DadosAgendamentoConsulta dados){
        System.out.println(dados);

        // classe controller nao deve ter regras de negocio. so controla o fluxo de execucao.
        /* Porem
         * 
         * Mas é importante ficarmos atentos, pois muitas vezes não é necessário criar um Service e, consequentemente, adicionar mais uma camada e complexidade desnecessária à nossa  aplicação. Uma regra que podemos utilizar é a seguinte: se não houverem regras de negócio, podemos simplesmente realizar a comunicação direta entre os controllers e os repositories da aplicação.
         * 
         */
        DadosDetalhamentoConsulta dadosDetalhamentoConsulta = agenda.agendar(dados);

        return ResponseEntity.ok(dadosDetalhamentoConsulta);
        
    }


    @DeleteMapping
    @Transactional
    public ResponseEntity<ResponseEntity.BodyBuilder> cancelarConsulta(@RequestBody @Valid DadosCancelamentoConsulta dados){
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }



}
