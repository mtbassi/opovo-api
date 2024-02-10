package br.com.mtbassi.opovo.api.infra.security;

import br.com.mtbassi.opovo.api.modules.journalists.entities.JournalistEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.name}")
    private String nameApplication;

    public String generateToken(JournalistEntity user) {
        try {
            return JWT.create()
                    .withIssuer(nameApplication)
                    .withSubject(user.getEmail())
                    .withExpiresAt(generateExpirationDate())
                    .sign(generateAlgorithm());
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while generating the token", e);
        }
    }

    public String validateToken(String token) {
        try {
            return JWT.require(generateAlgorithm())
                    .withIssuer(nameApplication)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }

    private Algorithm generateAlgorithm() {
        return Algorithm.HMAC256(secret);
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}