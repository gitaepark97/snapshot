package com.spring.snapshot.common.infrastructure.impl;

import com.spring.snapshot.common.infrastructure.TokenProvider;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Component
class JWTProvider implements TokenProvider {

    private final SecretKey secretKey;

    JWTProvider(@Value("${jwt.secret}") String secret) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key()
            .build()
            .getAlgorithm());
    }

    @Override
    public String issue(Map<String, Object> claims, Duration expiration) {
        Instant issuedAt = Instant.now();
        Instant expiresAt = issuedAt.plus(expiration);

        return Jwts.builder()
            .claims(claims)
            .signWith(secretKey)
            .issuedAt(Date.from(issuedAt))
            .expiration(Date.from(expiresAt))
            .compact();
    }

    @Override
    public Map<String, Object> parse(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }

}
