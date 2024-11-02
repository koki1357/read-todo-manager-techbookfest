package com.example.readtodomanager.repository;

import com.example.readtodomanager.model.Session;

public interface SessionRepository {

    /**
     * セッションを取得する
     */
    public Session getSession(String userId);

    /**
     * セッションを作成する
     */
    public void insert(Session session);

    /**
     * セッションを削除する
     */
    public void delete(String userId);
}
