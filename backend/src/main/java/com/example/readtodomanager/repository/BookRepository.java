package com.example.readtodomanager.repository;

import java.util.List;

import com.example.readtodomanager.model.Book;

public interface BookRepository {
    public List<Book> searchBooks(String title, String userId);

    public Book getBookDetails(Long id);

    public List<Book> findByUserId(String userId);

    public Book findById(Long id);

    public void delete(Long id);
    
    public void register(Book book);
}
