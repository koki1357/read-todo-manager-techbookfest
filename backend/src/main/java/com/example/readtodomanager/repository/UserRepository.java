package com.example.readtodomanager.repository;

import com.example.readtodomanager.model.User;

public interface UserRepository {

    /**
     * ユーザーを取得する
     */
    public User getUser(String userId);

    /**
     * ユーザーを作成する
     */
    public void insert(User user);
}
