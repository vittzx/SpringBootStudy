package com.vitor.estudo.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vitor.estudo.api.Medico.DadosCadastroMedico;
import com.vitor.estudo.api.Medico.Medico;
import com.vitor.estudo.api.Repository.MedicoRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("medicos")
public class MedicoController{

    @Autowired // injecao de depencias == autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional // quando o metodo eh POST, inserir, INSERT precisa colocar o transactional pois eh metodo de escrita 
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        // lembrar sempre de colocar o requestBody antes de enviar uma requisicao no Spring Boot.
        repository.save(new Medico(dados));
    }
}