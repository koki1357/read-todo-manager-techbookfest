package com.example.readtodomanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.readtodomanager.common.ApiResponse;
import com.example.readtodomanager.service.RegisterBookService;
import com.example.readtodomanager.service.dto.RegisterBookServiceInDto;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/books")
public class RegisterBookController {

    @Autowired
    private RegisterBookService registerBookService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerBook(@RequestBody RegisterBookServiceInDto registerBookServiceInDto, HttpServletRequest request) {
        registerBookServiceInDto.setUserId(request.getHeader("userId"));
        return registerBookService.registerBook(registerBookServiceInDto);
    }
}