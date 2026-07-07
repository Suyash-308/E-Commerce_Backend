package com.suyash.ecommerce_backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;


@Service
public class JwtService {

    // Secret Key (Production मध्ये application.properties मध्ये ठेवायची)
    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkey123456";

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // Generate JWT Token
    public String generateToken(String email) {

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract Email
    public String extractEmail(String token) {

        return extractAllClaims(token).getSubject();
    }

    // Validate Token
    public boolean isTokenValid(String token, String email) {

        return extractEmail(token).equals(email)
                && !isTokenExpired(token);
    }

    // Check Expiry
    private boolean isTokenExpired(String token) {

        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    // Read Claims
    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith((SecretKey) getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
