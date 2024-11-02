package com.example.readtodomanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.readtodomanager.model.Book;
import com.example.readtodomanager.repository.BookRepository;
import com.example.readtodomanager.service.dto.SearchBookServiceInDto;

@Service
public class SearchBookServiceImpl implements SearchBookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> searchBooks(SearchBookServiceInDto searchBookServiceInDto) {
        List<Book> books = bookRepository.searchBooks(searchBookServiceInDto.getTitle(),searchBookServiceInDto.getUserId());
        return books;
    }

}