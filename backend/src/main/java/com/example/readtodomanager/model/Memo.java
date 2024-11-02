package com.example.readtodomanager.model;

import java.util.Date;

import lombok.Data;

@Data
public class Memo {
    private Long id;
    private Long bookId;
    private String userId;
    private String content;
    private Date createdAt;
    private Date updatedAt;
}
