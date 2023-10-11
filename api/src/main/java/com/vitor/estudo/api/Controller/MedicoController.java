package com.vitor.estudo.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vitor.estudo.api.Medico.DadosCadastroMedico;
import com.vitor.estudo.api.Medico.Medico;
import com.vitor.estudo.api.Repository.MedicoRepository;


@RestController
@RequestMapping("medicos")
public class MedicoController{

    @Autowired // injecao de depencias == autowired
    private MedicoRepository repository;

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroMedico dados){
        // lembrar sempre de colocar o requestBody antes de enviar uma requisicao no Spring Boot.
        repository.save(new Medico(dados));
    }
}