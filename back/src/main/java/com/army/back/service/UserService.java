package com.army.back.service;

import com.army.back.mapper.UserMapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

import com.army.back.dto.User;
import com.army.back.enums.*;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void signUpUser(User user) {
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

    public void signInUser(String armyNumber, String rawPassword) {
        User user = userMapper.findByArmyNumber(armyNumber);
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("군번 또는 비밀번호가 올바르지 않습니다.");
        }
        // 로그인 성공 처리
    }
}
