package com.army.back.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.army.back.dto.SignUp;

@Mapper
public interface UserMapper {
    SignUp findByArmyNumber(String armyNumber);
    
    void insertUser(SignUp user);
}