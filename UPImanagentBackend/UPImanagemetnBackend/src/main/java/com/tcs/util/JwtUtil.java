package com.tcs.util;

import java.util.Date;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static final SecretKey SECRET_KEY =
        Keys.hmacShaKeyFor("THIS_IS_MY_SUPER_SECRET_JWT_KEY_256_BITS_LONG".getBytes());

    public String generateToken(String username, String role) {

        return Jwts.builder()
            .setSubject(username)
            .claim("role", "ROLE_"+role)   // "ADMIN" or "USER"
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000))
            .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
            .compact();
    }


    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
