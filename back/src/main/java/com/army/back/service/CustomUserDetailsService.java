package com.army.back.service;

import com.army.back.dto.SignUpDTO;
import com.army.back.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String armyNumber) throws UsernameNotFoundException {
        SignUpDTO user = userMapper.findByArmyNumber(armyNumber);
        if (user == null) {
            throw new UsernameNotFoundException("해당 군번의 사용자를 찾을 수 없습니다: " + armyNumber);
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getArmyNumber())
                .password(user.getPassword())
                .authorities(user.getRole())
                .build();
    }
}
