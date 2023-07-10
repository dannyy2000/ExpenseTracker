package com.example.expensetracker.security.services;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class JwtService {

    private String jwtSigningKey;

    public String extractUserEmail(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {

    }
}
