package com.example.readtodomanager.service;


import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.readtodomanager.common.ApiResponse;
import com.example.readtodomanager.common.StatusCode;
import com.example.readtodomanager.model.Book;
import com.example.readtodomanager.repository.BookRepository;
import com.example.readtodomanager.service.dto.DeleteBookServiceInDto;
import com.example.readtodomanager.validator.CommonValidator;

@Service
@Transactional
public class DeleteBookServiceImpl implements DeleteBookService {

    @Autowired
    private BookRepository bookRepository;


    /**
     * ①取得処理
     * deleteBookDao.getBooksを呼び出し、リクエストパラメータ.idと一致するデータをBooksテーブルから取得
     * ②業務チェック
     * なし（DBに一致する本がない場合は空振りとなる）
     * ③削除処理
     * deleteBookDao.deleteBooksを呼び出し、リクエストパラメータ.idと一致するデータをBooksテーブルから削除
     * ④200ステータスを詰めてreturn(ここは共通化したい)
     * 
     * @param id
     */
    @Override
    public ResponseEntity<ApiResponse<String>> deleteBook(DeleteBookServiceInDto serviceInDto) {
        
        // 検証を実行
        Set<ConstraintViolation<DeleteBookServiceInDto>> violations = CommonValidator.validate(serviceInDto);

        if (!violations.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse<String>(400, "不正なリクエストです"));
        }

        Long id = serviceInDto.getId();

        // ①取得処理
        Book book = bookRepository.findById(id);

        // ②業務チェック
        if (book == null) {
            return ResponseEntity.badRequest().body(new ApiResponse<String>(StatusCode.BAD_REQUEST, "本が存在しません"));
        }

        // ③削除処理
        bookRepository.delete(id);

        // ④200ステータスを詰めてreturn(ここは共通化したい)
        return ResponseEntity.ok(new ApiResponse<String>(StatusCode.OK, null));
    }
}
