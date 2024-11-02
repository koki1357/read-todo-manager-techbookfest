package com.example.readtodomanager.service.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class GetMemoServiceInDto {
    @NotNull
    Long bookId;
    String userId;
}

