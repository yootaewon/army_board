package com.army.back.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.army.back.service.CustomUserDetailsService;

import java.security.Key;
import java.util.Date;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class TokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.token-validity-in-seconds}")
    private long tokenValidityInSeconds;

    private Key key;

    private final CustomUserDetailsService userDetailsService;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(String armyNumber) {
        long now = System.currentTimeMillis();
        Date expiry = new Date(now + tokenValidityInSeconds * 1000);

        return Jwts.builder()
                .setSubject(armyNumber)
                .setIssuedAt(new Date(now))
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String createRefreshToken() {
        long now = System.currentTimeMillis();
        Date expiry = new Date(now + 14 * 24 * 60 * 60 * 1000); // 14일

        return Jwts.builder()
                .setIssuedAt(new Date(now))
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        String armyNumber = getArmyNumber(token);
        var userDetails = userDetailsService.loadUserByUsername(armyNumber);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public String getArmyNumber(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
