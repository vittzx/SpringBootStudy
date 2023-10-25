package com.vitor.estudo.api.Infra.Exception;


import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * Classe criada para a infraestrura do do projeto.
 *  Tratar erro 500 com 404
 * 
 * 
 * Tratar o erro 500 com 400 -> erro 400 com alguma coisa invalida
 */

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vitor.estudo.api.Infra.DTO.DadosErroValidacao;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice // muito importante para a validacao de erros
public class TratamentoErro {
    

    /**
     * @return not found 404
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException e){
        List<FieldError> erros = e.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }


}


