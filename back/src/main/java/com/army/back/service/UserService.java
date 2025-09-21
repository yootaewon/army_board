package com.army.back.service;

import com.army.back.mapper.UserMapper;
import com.army.back.model.User;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    
    public UserService(UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void insertUser(User user) {
        if (userMapper.findByArmyNumber(user.getArmyNumber()) != null) {
            throw new RuntimeException("이미 사용 중인 군번입니다.");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userMapper.insertUser(user);
    }
}
