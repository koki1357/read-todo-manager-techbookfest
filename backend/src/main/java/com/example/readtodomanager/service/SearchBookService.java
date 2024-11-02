package com.example.readtodomanager.service;

import java.util.List;

import com.example.readtodomanager.model.Book;
import com.example.readtodomanager.service.dto.SearchBookServiceInDto;

public interface SearchBookService {
    List<Book> searchBooks(SearchBookServiceInDto searchBookServiceInDto);
}