package com.challenge.clinicAPI.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.challenge.clinicAPI.model.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    //Esto luego debe ser cambiado a una variable de ambiente para mejorar seguridad.
    @Value("${api.security.secret}")
    private String apiSecret;

    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            String token = JWT.create()
                    .withIssuer("clinicAPI")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(tokenExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    private Instant tokenExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
