package com.vitor.estudo.api.Infra.Security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;



import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.vitor.estudo.api.Domain.Usuario.Usuario;

@Service
public class TokenService {
    

    public String createToken(Usuario usuario){
        try {   
            //                                             senha secreta
            Algorithm algorithm = Algorithm.HMAC256("SENHASECRETA");
            String token = JWT.create()
                .withIssuer("API ESTUDOS.VITORCOSSO") // ferramenta que é dona do token (QUAL API)
                .withSubject(usuario.getLogin()) // retorna o nome/login do usuario
                .withClaim("id", usuario.getId()) // retorna o id 
                .withExpiresAt(dataEspiracao())
                .sign(algorithm);

            return "TOKEN: " + token; // mensagem que retorna ao painel 200 no insmonima ->
            // TOKEN: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2FvLnBhdWxvQHRlc3QiLCJpc3MiOiJBUEkgRVNUVURPUy5WSVRPUkNPU1NPIiwiaWQiOjEsImV4cCI6MTY5OTMxNzA1Mn0.PhrDUBKiJGVg2bi7YTIdiMarubYxy954IQ2YxtsA_hk
        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar Token: ", exception);
        }
    }

    private Instant dataEspiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}

