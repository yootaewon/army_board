package com.army.back.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.army.back.dto.DischargeDate;
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
            LocalDate currentDate = LocalDate.now();
            long elapsedDays = ChronoUnit.DAYS.between(user.getEnlistmentDate(), currentDate);
            long totalDays = ChronoUnit.DAYS.between(user.getEnlistmentDate(), user.getDischargeDate());

            double completionPercentage = (double) elapsedDays / totalDays * 100;
            String formattedPercentage = String.format("%.4f", completionPercentage); 

            DischargeDate result = new DischargeDate();
            result.setEnlistmentDate(user.getEnlistmentDate());
            result.setDischargeDate(user.getDischargeDate());
            result.setPersent(formattedPercentage);

            return ResponseEntity.ok(result);
    }

}
