package com.example.readtodomanager.service;

import java.util.List;

import com.example.readtodomanager.model.Book;
import com.example.readtodomanager.service.dto.GetBookServiceInDto;

public interface GetBookService {
    public List<Book> getBook(GetBookServiceInDto getBookServiceInDto);
}
