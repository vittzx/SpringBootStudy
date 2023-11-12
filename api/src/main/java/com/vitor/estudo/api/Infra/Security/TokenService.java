package com.vitor.estudo.api.Infra.Security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import com.vitor.estudo.api.Domain.Usuario.Usuario;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    

    private String login;

    public String getLogin() {
        return login;
    }


    public String createToken(Usuario usuario){
        try {   
            //                                             senha secreta
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                .withIssuer("API ESTUDOS.VITORCOSSO") // ferramenta que é dona do token (QUAL API)
                .withSubject(usuario.getLogin()) // retorna o nome/login do usuario
                .withClaim("id", usuario.getId()) // retorna o id 
                .withExpiresAt(dataEspiracao())
                .sign(algorithm);
            login = usuario.getLogin();

            return token; // mensagem que retorna ao painel 200 no insmonima ->
            // TOKEN: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2FvLnBhdWxvQHRlc3QiLCJpc3MiOiJBUEkgRVNUVURPUy5WSVRPUkNPU1NPIiwiaWQiOjEsImV4cCI6MTY5OTMxNzA1Mn0.PhrDUBKiJGVg2bi7YTIdiMarubYxy954IQ2YxtsA_hk
        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar Token: ", exception);
        }
    }


    public String getSubject(String tokenJWT){ // pega o usuario.login()
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String subject = JWT.require(algorithm)
                .withIssuer("API ESTUDOS.VITORCOSSO")
                .build()
                .verify(tokenJWT)
                .getSubject();
            
            return subject;
                
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }


    private Instant dataEspiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}

