package com.army.back.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.army.back.dto.DischargeDate;
import com.army.back.dto.SignUp;
import com.army.back.jwt.TokenProvider;
import com.army.back.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DichargeService {

    private final TokenProvider tokenProvider;
    private final UserMapper userMapper;
    
    public ResponseEntity<?> selectDichargeDate(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Access Token이 없습니다.");
        }

        String token = authHeader.substring(7);
        String armyNumber;
        try {
            armyNumber = tokenProvider.extractArmyNumber(token); // 토큰에서 군번 추출
        } catch (Exception e) {
            return ResponseEntity.status(401).body("유효하지 않은 Access Token입니다.");
        }

        SignUp user = userMapper.findByArmyNumber(armyNumber);
        if (user == null) {
            return ResponseEntity.status(404).body("유효하지 않은 군번입니다.");
        }

        LocalDate currentDate = LocalDate.now();
        long currentDays = ChronoUnit.DAYS.between(user.getEnlistmentDate(), currentDate) + 1;  // 입대일부터 현재까지의 일수
        long totalDays = ChronoUnit.DAYS.between(user.getEnlistmentDate(), user.getDischargeDate()) + 1;  // 입대일부터 전역일까지의 일수

        double completionPercentage = (double) currentDays / totalDays * 100;
        String formattedPercentage = String.format("%.4f", completionPercentage); // 소수점 4자리까지 표현

        DischargeDate result = new DischargeDate();
        result.setEnlistmentDate(user.getEnlistmentDate());
        result.setDischargeDate(user.getDischargeDate());
        result.setPersent(formattedPercentage); 
        result.setTotalDays(totalDays);
        result.setCurrentDays(currentDays);
        result.setRemaingDays(totalDays - currentDays);

        return ResponseEntity.ok(result);
    }
}
