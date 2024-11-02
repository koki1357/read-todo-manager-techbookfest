package com.example.readtodomanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.readtodomanager.common.ApiResponse;
import com.example.readtodomanager.service.DeleteBookService;
import com.example.readtodomanager.service.dto.DeleteBookServiceInDto;

@RestController
@RequestMapping("/api/books")
public class DeleteBookController {
    @Autowired
    private DeleteBookService deleteBookService;

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteBook(@PathVariable Long id) {
        DeleteBookServiceInDto deleteBookServiceInDto = new DeleteBookServiceInDto();
        deleteBookServiceInDto.setId(id);
        return deleteBookService.deleteBook(deleteBookServiceInDto);
    }
}
