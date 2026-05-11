package com.example.cixoil.security;

import com.example.cixoil.dto.AuthUserDTO;
import com.example.cixoil.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    @Getter
    @Value("${jwt.expiration}")
    private Long expiration;

    @Getter
    @Value("${jwt.refresh-expiration}")
    private Long refreshExpiration;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(AuthUserDTO authUserDTO) {
        if (authUserDTO.role() == null) {
            throw new IllegalStateException("El usuario no tiene ningún rol asignado");
        }

        return Jwts.builder()
                .setSubject(String.valueOf(authUserDTO.id()))
                .claim("username", authUserDTO.username())
                .claim("role", authUserDTO.role())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(AuthUserDTO authUserDTO) {
        return Jwts.builder()
                .setSubject(String.valueOf(authUserDTO.id()))
                .claim("type", "refresh")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("Token JWT inválido");
        }
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isAccessTokenValid(String token, AuthUserDTO authUserDTO) {
        return extractUserId(token).equals(String.valueOf(authUserDTO.id()))
                && !isTokenExpired(token);
    }

    public boolean isRefreshToken(String token) {
        return "refresh".equals(extractClaim(token, c -> c.get("type")));
    }

    public boolean isRefreshTokenValid(String token, AuthUserDTO authUserDTO) {
        return isRefreshToken(token)
                && extractUserId(token).equals(String.valueOf(authUserDTO.id()))
                && !isTokenExpired(token);
    }
}
