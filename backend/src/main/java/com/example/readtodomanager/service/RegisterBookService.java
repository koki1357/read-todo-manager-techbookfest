package com.example.readtodomanager.service;

import org.springframework.http.ResponseEntity;

import com.example.readtodomanager.common.ApiResponse;
import com.example.readtodomanager.service.dto.RegisterBookServiceInDto;

public interface RegisterBookService {
    ResponseEntity<ApiResponse<String>> registerBook(RegisterBookServiceInDto registerBookServiceInDto);
}