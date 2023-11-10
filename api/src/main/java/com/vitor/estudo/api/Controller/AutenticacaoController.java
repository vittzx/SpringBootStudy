package com.vitor.estudo.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitor.estudo.api.Domain.Usuario.Usuario;
import com.vitor.estudo.api.Domain.Usuario.DTO.DadosAutenticacaoUsuario;
import com.vitor.estudo.api.Infra.Security.TokenService;
import com.vitor.estudo.api.Infra.Security.DTO.DadosTokenJWT;

import jakarta.validation.Valid;

@RestController
@RequestMapping("login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid DadosAutenticacaoUsuario dados){
        UsernamePasswordAuthenticationToken autorizacaoToken  = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(autorizacaoToken);
        String tokenJWT = tokenService.createToken((Usuario) authentication.getPrincipal());
        // devolver o token
        DadosTokenJWT dadosToken = new DadosTokenJWT(tokenJWT);
        return ResponseEntity.ok(dadosToken);
    }
    
}
