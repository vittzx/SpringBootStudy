package com.vitor.estudo.api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitor.estudo.api.Medico.DadosAtualizacaoMedico;
import com.vitor.estudo.api.Medico.DadosCadastroMedico;
import com.vitor.estudo.api.Medico.DadosListagemMedico;
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


    @GetMapping
    public List<DadosListagemMedico> listar(@PageableDefault(size =10, sort={"nome"})Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new).toList();

        // return repository.findAll().stream().map(DadosListagemMedico::new).toList();
        // no retorno do repository.findAll, ele retorna do banco de dados uma lista de Objeto Medico
        // por isso  tem que colocar o Stream e fazer o map DadosListagemMedico para cada um item da lista, como parametro imbutido o Medico.
        // o Parametro medico fica 'escondido'.
        // ai no final convertemos para list. 
    } 


    @PutMapping // atualiando 
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id()); // carregar o medico pelo id
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        repository.deleteById(id); // exlcusao do banco de dados.
    }


    @DeleteMapping("/logic-delete/{id}")
    @Transactional
    public void logicDelete(@PathVariable Long id){
        var medico = repository.getReferenceById(id); // exlcusao do banco de dados.
        medico.excluirLogico();
    }

}