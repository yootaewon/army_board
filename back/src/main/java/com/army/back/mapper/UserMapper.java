package com.army.back.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.army.back.dto.SignUpDTO;

@Mapper
public interface UserMapper {
    SignUpDTO findByArmyNumber(String armyNumber);
    void insertUser(SignUpDTO user);
}