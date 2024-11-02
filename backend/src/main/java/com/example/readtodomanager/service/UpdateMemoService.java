package com.example.readtodomanager.service;

import org.springframework.http.ResponseEntity;

import com.example.readtodomanager.common.ApiResponse;
import com.example.readtodomanager.service.dto.UpdateMemoServiceInDto;

public interface UpdateMemoService {

    public ResponseEntity<ApiResponse<Long>> updateBookMemo(UpdateMemoServiceInDto updateBookMemoServiceInDto);
}
