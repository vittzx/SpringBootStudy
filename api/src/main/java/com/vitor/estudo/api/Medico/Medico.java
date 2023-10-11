package com.vitor.estudo.api.Medico;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Entidade JPA
// Definicao para salvar no Banco de Dados

@Entity
@Table(name="medicos")
public class Medico {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) // incrementa o valor 
    private Long id;

    private String nome;
    private String email;
    private String crm;
    private Especialidade especialidade;
    private Endereco endereco; 
}
