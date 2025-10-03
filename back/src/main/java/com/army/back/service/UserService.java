package com.army.back.service;

import com.army.back.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import com.army.back.dto.JwtToken;
import com.army.back.dto.SignIn;
import com.army.back.dto.SignUp;
import com.army.back.enums.ArmyType;
import com.army.back.jwt.TokenProvider;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final LeaveService leaveService;

    public void signUpUser(SignUp user) {
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
        leaveService.registerAnnualLeave(user, armyType);
    }

    public JwtToken signInUser(SignIn signInDTO) {
        SignUp user = userMapper.findByArmyNumber(signInDTO.getArmyNumber());
        if (user == null || !passwordEncoder.matches(signInDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("군번 또는 비밀번호가 일치하지 않습니다.");
        }
        String accessToken = tokenProvider.generateAccessToken(signInDTO.getArmyNumber());
        String refreshToken = tokenProvider.generateRefreshToken(signInDTO.getArmyNumber());

        return new JwtToken(accessToken, refreshToken);
    }
}
