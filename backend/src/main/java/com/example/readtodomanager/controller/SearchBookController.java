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
import com.example.readtodomanager.service.SearchBookService;
import com.example.readtodomanager.service.dto.SearchBookServiceInDto;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/search/books")
public class SearchBookController {

    @Autowired
    private SearchBookService searchBookService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<Book>>> searchBooks(@RequestParam String title, HttpServletRequest request) {
        SearchBookServiceInDto searchBookServiceInDto = new SearchBookServiceInDto();
        searchBookServiceInDto.setUserId(request.getHeader("userId"));
        searchBookServiceInDto.setTitle(title);
        List<Book> books = searchBookService.searchBooks(searchBookServiceInDto);
        
        return ResponseEntity.ok(new ApiResponse<>(StatusCode.OK, null, books));
    }
}