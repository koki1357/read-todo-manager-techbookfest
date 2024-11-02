package com.example.readtodomanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.readtodomanager.common.ApiResponse;
import com.example.readtodomanager.common.StatusCode;
import com.example.readtodomanager.model.Memo;
import com.example.readtodomanager.service.GetMemoService;
import com.example.readtodomanager.service.dto.GetMemoServiceInDto;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/memo")
public class GetMemoController {

    @Autowired
    private GetMemoService getMemoService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<Memo>>> getMemos(@RequestParam Long bookId, HttpServletRequest request) {
        GetMemoServiceInDto getMemoServiceInDto = new GetMemoServiceInDto();
        getMemoServiceInDto.setBookId(bookId);
        getMemoServiceInDto.setUserId(request.getHeader("userId"));
        List<Memo> memos = getMemoService.getMemoList(getMemoServiceInDto);
        return ResponseEntity.ok(new ApiResponse<List<Memo>>(StatusCode.OK, null, memos));
    }

}