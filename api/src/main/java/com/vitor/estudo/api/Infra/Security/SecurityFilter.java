package com.vitor.estudo.api.Infra.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vitor.estudo.api.Domain.Usuario.Repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {



    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)  throws ServletException, IOException {

        String tokenJWT = recuperarToken(request);
        if(tokenJWT != null){
            String subject = tokenService.getSubject(tokenJWT);// usuario 
            UserDetails usuario = repository.findByLogin(subject);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response); // para continuar o fluxo da requisicao ||| Para bloquear a requisicao, nao eh para chamar esse filtro


    }



    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization"); // recuperando o token no header.
        if(token != null){  
            return token.replace("Bearer ","");
        }
        
        return null;
    }


    
}
