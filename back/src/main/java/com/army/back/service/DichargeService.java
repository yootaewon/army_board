package com.army.back.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.army.back.dto.SignUpDTO;
import com.army.back.jwt.TokenProvider;
import com.army.back.mapper.UserMapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DichargeService {

    private final TokenProvider tokenProvider;
    private final UserMapper userMapper;
    
    public ResponseEntity<?> selectDichargeDate(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body("Access Token이 없습니다.");
            }

            String token = authHeader.substring(7);
            String armyNumber = tokenProvider.extractArmyNumber(token);

            SignUpDTO user = userMapper.findByArmyNumber(armyNumber);
            if (user == null) {
                return ResponseEntity.status(404).body("사용자를 찾을 수 없습니다.");
            }

            Map<String, LocalDate> result = new HashMap<>();
            result.put("enlistmentDate", user.getEnlistmentDate());
            result.put("dischargeDate", user.getDischargeDate());
            return ResponseEntity.ok(result);
    }

}
