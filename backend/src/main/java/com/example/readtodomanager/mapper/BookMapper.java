package com.example.readtodomanager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.readtodomanager.model.Book;
import com.example.readtodomanager.model.Memo;

@Mapper
public interface BookMapper {
    Memo getMemo(Long bookId);

    void update(Memo book);

    List<Book> findByUserId(String userId);

    List<Book> searchBooks(String title, String userId);

    Book getBookDetails(Long id);

    Book findById(Long id);

    void delete(Long id);

    void register(Book book);

}
