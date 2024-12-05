package com.Tavin.bookstore.infra.security.token;

import com.Tavin.bookstore.model.UserModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secretKey;

    public String generatedToken(UserModel user) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String token = JWT.create()
                .withIssuer("BookStore")
                .withSubject(user.getLogin())
                .withExpiresAt(instantFromToken())
                .sign(algorithm);
        return token;
    }

    public String ValidateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.require(algorithm)
                .withIssuer("BookStore")
                .build()
                .verify(token)
                .getSubject();
    }

    public Instant instantFromToken() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
