package com.vitor.estudo.api.Medico;

import com.vitor.estudo.api.Endereco.Endereco;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Entidade JPA
// Definicao para salvar no Banco de Dados

@Entity
@Table(name="medicos")
@Getter // Gera getters e Setters atraves do Lombok
@NoArgsConstructor // Gera um construtor sem argumentos 
@AllArgsConstructor // gera um construtor com todos os argumentos
@EqualsAndHashCode(of="id") // gera um equals em cima do id 
public class Medico {
    

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // incrementa o valor 
    private Long id;

    private String nome;
    private String email;
    private String crm;
    
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco; 

    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());    
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoMedico dados) {
        if(dados.nome() != null) this.nome= dados.nome();
        if (dados.Endereco() != null) this.endereco.atualizarInformacoes(dados.Endereco());
        
    }
}
