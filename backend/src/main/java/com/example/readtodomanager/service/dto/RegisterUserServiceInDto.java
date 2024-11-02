package com.example.readtodomanager.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterUserServiceInDto {
    @NotNull
    private String userId;
    @NotNull
    @Size(max = 16)
    private String password;
}
