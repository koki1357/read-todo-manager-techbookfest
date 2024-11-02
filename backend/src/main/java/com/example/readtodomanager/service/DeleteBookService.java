package com.example.readtodomanager.service;

import org.springframework.http.ResponseEntity;

import com.example.readtodomanager.common.ApiResponse;
import com.example.readtodomanager.service.dto.DeleteBookServiceInDto;

public interface DeleteBookService {
    public ResponseEntity<ApiResponse<String>> deleteBook(DeleteBookServiceInDto serviceInDto);
}
