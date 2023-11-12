package com.vitor.estudo.api.Controller;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.vitor.estudo.api.Domain.Medico.Medico;
import com.vitor.estudo.api.Domain.Medico.DTO.DadosAtualizacaoMedico;
import com.vitor.estudo.api.Domain.Medico.DTO.DadosCadastroMedico;
import com.vitor.estudo.api.Domain.Medico.DTO.DadosDetalhamentoMedico;
import com.vitor.estudo.api.Domain.Medico.DTO.DadosListagemMedico;
import com.vitor.estudo.api.Domain.Medico.Repository.MedicoRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("medicos")
public class MedicoController{

    @Autowired // injecao de depencias == autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional // quando o metodo eh POST, inserir, INSERT precisa colocar o transactional pois eh metodo de escrita 
    public ResponseEntity<DadosDetalhamentoMedico> cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
        // lembrar sempre de colocar o requestBody antes de enviar uma requisicao no Spring Boot.
        Medico medico = new Medico(dados);
        repository.save(medico);
        // Uri Buildeer para pegar a url (endereco )
        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }


    @GetMapping
    public ResponseEntity<List<DadosListagemMedico>> listar(@PageableDefault(size =10, sort={"nome"})Pageable paginacao){
        var page =  repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new).toList(); 
        return ResponseEntity.ok(page); // retorna o codigo 200

        // return repository.findAll().stream().map(DadosListagemMedico::new).toList();
        // no retorno do repository.findAll, ele retorna do banco de dados uma lista de Objeto Medico
        // por isso  tem que colocar o Stream e fazer o map DadosListagemMedico para cada um item da lista, como parametro imbutido o Medico.
        // o Parametro medico fica 'escondido'.
        // ai no final convertemos para list. 
    } 

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedico> pegarMedicoId(@PathVariable Long id){
        Medico medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    } 


    @PutMapping // atualiando 
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        Medico medico = repository.getReferenceById(dados.id()); // carregar o medico pelo id
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico)); // nao eh recomendado retornar entidade JPA.
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        repository.deleteById(id); // exlcusao do banco de dados.
    }


    @DeleteMapping("/logic-delete/{id}")
    @Transactional
    // Metodo excluir, com boa pratica deve retornar codigo 204 
    public ResponseEntity<ResponseEntity.BodyBuilder> logicDelete(@PathVariable Long id){
        var medico = repository.getReferenceById(id); // exlcusao do banco de dados.
        medico.excluirLogico();
        return ResponseEntity.noContent().build();
    }

}