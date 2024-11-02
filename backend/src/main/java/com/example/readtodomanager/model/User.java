package com.example.readtodomanager.model;

import java.util.Date;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String userId;
    private String password;
    private Date createdAt;
    private Date updatedAt;
}
