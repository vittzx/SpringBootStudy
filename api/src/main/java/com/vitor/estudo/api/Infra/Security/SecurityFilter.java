package com.vitor.estudo.api.Infra.Security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)  throws ServletException, IOException {

        String token = recuperarToken(request);
        System.out.println(token);

        filterChain.doFilter(request, response); // para continuar o fluxo da requisicao ||| Para bloquear a requisicao, nao eh para chamar esse filtro
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization"); // recuperando o token no header.
        if(token == null){ // se nao conseguiu recuperar o token
            throw new RuntimeException("TOKEN JWT não conseguiu ser recuperado pelo Header da Authorization ");
        }
        
        return token.replace("Bearer ","");
    }


    
}
