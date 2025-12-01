package com.example.rentyourtool.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    private final Key key;
    private final long accessTokenValiditySeconds;

    public JwtService(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.access-validity-seconds:900}") long accessTokenValiditySeconds) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public String createAccessToken(Long userId, String email) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(accessTokenValiditySeconds);

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("email", email)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(exp))
                .signWith(key)
                .compact();
    }

    public Claims parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isValid(String token) {
        try {
            Claims claims = parse(token);
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public Long getUserId(String token) {
        return Long.valueOf(parse(token).getSubject());
    }

    public String getEmail(String token) {
        return parse(token).get("email", String.class);
    }

    public long getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }
}