package com.example.readtodomanager.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.readtodomanager.model.Session;

@Mapper
public interface SessionMapper {
    public Session findByUserId(String userId);

    public void insert(Session session);

    public void delete(String userId);

}
