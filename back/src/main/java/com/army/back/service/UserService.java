package com.army.back.service;

import com.army.back.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.army.back.dto.SignUpDTO;
import com.army.back.enums.*;
import com.army.back.jwt.TokenProvider;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public void signUpUser(SignUpDTO user) {
        if (userMapper.findByArmyNumber(user.getArmyNumber()) != null) {
            throw new RuntimeException("이미 사용 중인 군번입니다.");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        
        ArmyType armyType = user.getArmyType(); 
        LocalDate enlistmentDate = user.getEnlistmentDate();
        LocalDate dischargeDate = armyType.calculateDischargeDate(enlistmentDate);
        user.setDischargeDate(dischargeDate);

        userMapper.insertUser(user);
    }

     public Map<String, String> signInUser(String armyNumber, String rawPassword) {
        SignUpDTO user = userMapper.findByArmyNumber(armyNumber);
        if (user == null || !passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("군번 또는 비밀번호가 올바르지 않습니다.");
        }

        String accessToken = tokenProvider.createAccessToken(armyNumber);
        String refreshToken = tokenProvider.createRefreshToken();

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }
}
