package com.vitor.estudo.api.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vitor.estudo.api.Medico.DadosCadastroMedico;


@RestController
@RequestMapping("medicos")
public class MedicoController{

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroMedico dados){
        // lembrar sempre de colocar o requestBody antes de enviar uma requisicao no Spring Boot.
        System.out.println(dados);
    }
}