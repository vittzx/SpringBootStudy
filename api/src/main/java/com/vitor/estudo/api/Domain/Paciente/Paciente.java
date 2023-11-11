package com.vitor.estudo.api.Domain.Paciente;

import com.vitor.estudo.api.Domain.Endereco.Endereco;
import com.vitor.estudo.api.Domain.Paciente.DTO.DadosCadastroPaciente;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="Paciente")
@Table(name="pacientes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of="id")
public class Paciente {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String cpf;
    
    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Paciente(DadosCadastroPaciente dados){
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco())  ;
    }

}
