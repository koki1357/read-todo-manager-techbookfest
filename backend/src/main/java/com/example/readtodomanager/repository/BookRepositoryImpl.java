package com.example.readtodomanager.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.readtodomanager.mapper.BookMapper;
import com.example.readtodomanager.model.Book;

@Repository
public class BookRepositoryImpl implements BookRepository {
    @Autowired
    private BookMapper bookMapper;

    public List<Book> searchBooks(String title, String userId) {
        return bookMapper.searchBooks(title, userId);
    }

    public Book getBookDetails(Long id) {
        return bookMapper.getBookDetails(id);
    }

    public List<Book> findByUserId(String userId) {
        return bookMapper.findByUserId(userId);
    }

    public Book findById(Long id) {
        return bookMapper.findById(id);
    }

    public void delete(Long id) {
        bookMapper.delete(id);
    }
    
    public void register(Book book) {
        bookMapper.register(book);
    }
}