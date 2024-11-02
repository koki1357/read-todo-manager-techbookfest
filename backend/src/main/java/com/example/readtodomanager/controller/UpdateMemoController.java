package com.example.readtodomanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.readtodomanager.common.ApiResponse;
import com.example.readtodomanager.controller.dto.UpdateMemoInDto;
import com.example.readtodomanager.service.UpdateMemoService;
import com.example.readtodomanager.service.dto.UpdateMemoServiceInDto;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/v1/memo")
public class UpdateMemoController {

    @Autowired
    private UpdateMemoService updateBookMemoService;

    @PutMapping("/")
    public ResponseEntity<ApiResponse<Long>> updateBookMemo(@RequestBody UpdateMemoInDto dto, HttpServletRequest request) {
        UpdateMemoServiceInDto input = new UpdateMemoServiceInDto();
        input.setId(dto.getId());
        input.setUserId(request.getHeader("userId"));
        input.setContent(dto.getContent());

        return updateBookMemoService.updateBookMemo(input);
    }
}
