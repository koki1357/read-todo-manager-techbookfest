package com.example.readtodomanager.service;

import org.springframework.http.ResponseEntity;

import com.example.readtodomanager.common.ApiResponse;
import com.example.readtodomanager.service.dto.LogoutInDto;

public interface LogoutService {
    public ResponseEntity<ApiResponse<?>> logout(LogoutInDto request);
}
