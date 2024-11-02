package com.example.readtodomanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.readtodomanager.common.ApiResponse;
import com.example.readtodomanager.controller.dto.CreateMemoInDto;
import com.example.readtodomanager.service.CreateMemoService;
import com.example.readtodomanager.service.dto.CreateMemoServiceInDto;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/memo")
public class CreateMemoController {

    @Autowired
    private CreateMemoService createMemoService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> createMemo(@RequestBody CreateMemoInDto memo, HttpServletRequest request) {
        CreateMemoServiceInDto input = new CreateMemoServiceInDto();
        input.setBookId(memo.getBookId());
        input.setUserId(request.getHeader("userId"));
        input.setContent(memo.getContent());
        return createMemoService.createMemo(input);
    }
}
