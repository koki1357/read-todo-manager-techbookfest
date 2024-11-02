package com.example.readtodomanager.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.readtodomanager.mapper.UserMapper;
import com.example.readtodomanager.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository  {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(String userId) {
        return userMapper.findByUserId(userId);
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

}
