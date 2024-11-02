package com.example.readtodomanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.readtodomanager.model.Book;
import com.example.readtodomanager.repository.BookRepository;
import com.example.readtodomanager.service.dto.GetBookServiceInDto;

/**
 * Service implementation for retrieving books.
 */
@Service
public class GetBookServiceImpl implements GetBookService {

    @Autowired
    private BookRepository searchBookRepository;

    /**
     * 指定された条件に基づいて書籍のリストを取得します。
     *
     * @param getBookServiceInDto 検索条件を含む入力DTO。
     * @return 検索条件に一致する書籍のリスト。入力DTOに 'id' フィールドが指定されている場合、
     *         一致するIDを持つ書籍のみが返されます。それ以外の場合、指定されたユーザーIDに関連する
     *         すべての書籍が返されます。
     */
    @Override
    public List<Book> getBook(GetBookServiceInDto getBookServiceInDto) {
       Long id = getBookServiceInDto.getId();
        List<Book> books = searchBookRepository.findByUserId(getBookServiceInDto.getUserId());
        if (id != null) {
            return books.stream().filter(book -> book.getId().equals(id)).collect(Collectors.toList());
        }
        return books;
    }

}
