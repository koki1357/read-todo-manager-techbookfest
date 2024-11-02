package com.example.readtodomanager.service;

import org.springframework.http.ResponseEntity;

import com.example.readtodomanager.common.ApiResponse;
import com.example.readtodomanager.service.dto.CreateMemoServiceInDto;

public interface CreateMemoService {
    public ResponseEntity<ApiResponse<String>> createMemo(CreateMemoServiceInDto createMemoServiceInDto);
}
