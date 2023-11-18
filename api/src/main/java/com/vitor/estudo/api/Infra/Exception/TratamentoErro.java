package com.vitor.estudo.api.Infra.Exception;


import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
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

import com.vitor.estudo.api.Domain.Consultas.Service.ValidacaoExecption;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice // muito importante para a validacao de erros
public class TratamentoErro {
    

    /**
     * @return not found 404
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseEntity.BodyBuilder> tratarErro404(){
        return ResponseEntity.notFound().build();
    }
    
    @ExceptionHandler(ValidacaoExecption.class)
    public ResponseEntity<String> tratarErroRegraNegocio404(ValidacaoExecption e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErro400(MethodArgumentNotValidException e){
        List<FieldError> erros = e.getFieldErrors();
        List<DadosErroValidacao> Lista_DadosErroValidacao =erros.stream().map(DadosErroValidacao::new).toList();
        return ResponseEntity.badRequest().body(Lista_DadosErroValidacao);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?>tratarErro400(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> tratarErroBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> tratarErroAuthentication() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> tratarErroAcessoNegado() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> tratarErro500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " +ex.getLocalizedMessage());
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}


