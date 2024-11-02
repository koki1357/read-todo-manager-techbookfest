package com.example.readtodomanager.service;

import org.springframework.http.ResponseEntity;

import com.example.readtodomanager.common.ApiResponse;
import com.example.readtodomanager.service.dto.DeleteMemoServiceInDto;

public interface DeleteMemoService {
    ResponseEntity<ApiResponse<Long>> deleteMemo(DeleteMemoServiceInDto deleteMemoServiceInDto);
}
