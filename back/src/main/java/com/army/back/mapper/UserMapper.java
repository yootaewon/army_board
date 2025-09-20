package com.army.back.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.army.back.model.User;

@Mapper
public interface UserMapper {
    User findByArmyNumber(String armyNumber);
    void insertUser(User user);
}