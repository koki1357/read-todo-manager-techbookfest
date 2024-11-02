package com.example.readtodomanager.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.readtodomanager.model.User;

@Mapper
public interface UserMapper {
    User findByUserId(String userId);
    void insert(User user);
}
