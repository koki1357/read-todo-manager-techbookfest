package com.example.readtodomanager.service;

import org.springframework.http.ResponseEntity;

import com.example.readtodomanager.common.ApiResponse;
import com.example.readtodomanager.service.dto.RegisterUserServiceInDto;

public interface RegisterUserService {
    public ResponseEntity<ApiResponse<?>> registerUser(RegisterUserServiceInDto inDto);
}
