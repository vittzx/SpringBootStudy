package com.vitor.estudo.api.Endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Entidade JPA - Endereco | Salvar no Banco de DADOS !!
// Class embeddable serve para estar na mesma tabela mas com itens separados.
@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;
    

}
