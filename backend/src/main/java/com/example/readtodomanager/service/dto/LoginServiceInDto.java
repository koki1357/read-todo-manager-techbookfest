package com.example.readtodomanager.service.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginServiceInDto {
    @NotNull
    String userId;
    @NotNull
    String password;
}
