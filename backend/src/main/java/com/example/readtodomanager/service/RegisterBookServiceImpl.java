package com.example.readtodomanager.service;


import javax.validation.ConstraintViolation;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.readtodomanager.common.ApiResponse;
import com.example.readtodomanager.common.StatusCode;
import com.example.readtodomanager.model.Book;
import com.example.readtodomanager.repository.BookRepository;
import com.example.readtodomanager.service.dto.RegisterBookServiceInDto;
import com.example.readtodomanager.validator.CommonValidator;

@Service
@Transactional
public class RegisterBookServiceImpl implements RegisterBookService {

    @Autowired
    private BookRepository registerBookRepository;

    @Override
    public ResponseEntity<ApiResponse<String>> registerBook(RegisterBookServiceInDto registerBookServiceInDto) {
        // 検証を実行
        Set<ConstraintViolation<RegisterBookServiceInDto>> violations = CommonValidator.validate(registerBookServiceInDto);

        if (!violations.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse<String>(StatusCode.BAD_REQUEST, "不正なリクエストです"));
        }
        
        Book newBook = new Book();
        newBook.setUserId(registerBookServiceInDto.getUserId());
        newBook.setTitle(registerBookServiceInDto.getTitle());
        newBook.setImage(registerBookServiceInDto.getImage());
        newBook.setAuthor(registerBookServiceInDto.getAuthor());
        newBook.setStartDate(registerBookServiceInDto.getStartDate());
        newBook.setFinishDate(registerBookServiceInDto.getFinishDate());
        registerBookRepository.register(newBook);

        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Book registered successfully", null));
    }
}