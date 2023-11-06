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

            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar Token: ", exception);
        }
    }

    private Instant dataEspiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}

