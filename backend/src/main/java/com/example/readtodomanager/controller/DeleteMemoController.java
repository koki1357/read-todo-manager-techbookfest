package com.example.readtodomanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.readtodomanager.common.ApiResponse;
import com.example.readtodomanager.service.DeleteMemoService;
import com.example.readtodomanager.service.dto.DeleteMemoServiceInDto;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/memo")
public class DeleteMemoController {
    @Autowired
    private DeleteMemoService deleteMemoService;

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Long>> deleteMemo(@PathVariable Long id, HttpServletRequest request) {
        DeleteMemoServiceInDto deleteMemoServiceInDto = new DeleteMemoServiceInDto();
        deleteMemoServiceInDto.setId(id);
        deleteMemoServiceInDto.setUserId(request.getHeader("userId"));
        return deleteMemoService.deleteMemo(deleteMemoServiceInDto);
    }
}
