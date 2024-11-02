package com.example.readtodomanager.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.readtodomanager.mapper.SessionMapper;
import com.example.readtodomanager.model.Session;

@Repository
public class SessionRepositoryImpl implements SessionRepository {

    @Autowired
    private SessionMapper sessionMapper;

    @Override
    public Session getSession(String userId) {
        return sessionMapper.findByUserId(userId);
    }

    @Override
    public void insert(Session session) {
        sessionMapper.insert(session);
    }

    @Override
    public void delete(String userId) {
        sessionMapper.delete(userId);
    }

}
