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
import com.example.readtodomanager.model.Book;
import com.example.readtodomanager.service.GetBookService;
import com.example.readtodomanager.service.dto.GetBookServiceInDto;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/books")
public class GetBookController {

    @Autowired
    private GetBookService getBookService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<Book>>> getBookDetails(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
        GetBookServiceInDto getBookServiceInDto = new GetBookServiceInDto();
        getBookServiceInDto.setUserId(request.getHeader("userId"));
        getBookServiceInDto.setId(id);
        List<Book> books = getBookService.getBook(getBookServiceInDto);
        return ResponseEntity.ok(new ApiResponse<>(StatusCode.OK, null, books)); 
    }

}
