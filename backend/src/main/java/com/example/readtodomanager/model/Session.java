package com.example.readtodomanager.model;

import java.util.Date;

import lombok.Data;

@Data
public class Session {
    private Long id;
    private String userId;
    private String token;
    private Date expiredAt;
    private Date createdAt;
    private Date updatedAt;

}
