package com.example.readtodomanager.controller.dto;

import lombok.Value;

@Value
public class CreateMemoInDto {
    private Long bookId;
    private String content;
}
