package com.army.back.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenProvider {

    private final Key key; 

    @Value("${jwt.access-token-validity-in-seconds}")
    private long accessTokenExpiration;

    @Value("${jwt.refresh-token-validity-in-seconds}")
    private long refreshTokenExpiration;

    public TokenProvider() {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String generateAccessToken(String armyNumber){
        return Jwts.builder()
            .setSubject(armyNumber)
            .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration * 1000)) 
            .signWith(key) 
            .compact();
    }

    public String generateRefreshToken(String armyNumber){
        return Jwts.builder()
            .setSubject(armyNumber)
            .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration * 1000)) 
            .signWith(key)  
            .compact();
    }

    public String extractArmyNumber(String token){
        return Jwts.parser()
            .setSigningKey(key)  
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    private Date extractExpiration(String token){
        return Jwts.parser()
            .setSigningKey(key)  
            .parseClaimsJws(token)
            .getBody()
            .getExpiration();
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token){
        String armyNumber = extractArmyNumber(token);
        return armyNumber != null && !isTokenExpired(token);
    }
}
