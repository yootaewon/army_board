package com.army.back.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.army.back.jwt.TokenProvider;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReissueService {
    
    private final TokenProvider tokenProvider;

    public ReissueService(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    public ResponseEntity<String> reissue(HttpServletRequest request, HttpServletResponse response) {

        String refresh=null;
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("refreshToken")){
                refresh=cookie.getValue();
            }
        }

        if(refresh==null){
            return ResponseEntity.badRequest().body("Refresh 토큰이 없습니다.");
        }

        try{
            tokenProvider.isTokenExpired(refresh);
        }catch(ExpiredJwtException e){
            return ResponseEntity.badRequest().body("refresh 토큰이 만료되었습니다.");
        }

        String armyNumber=tokenProvider.extractArmyNumber(refresh);
        String newAccessToken=tokenProvider.generateAccessToken(armyNumber);

        return ResponseEntity.ok(newAccessToken);

    }
}