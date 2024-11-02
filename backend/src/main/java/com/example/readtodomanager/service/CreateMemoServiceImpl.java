package com.example.readtodomanager.service;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.readtodomanager.common.ApiResponse;
import com.example.readtodomanager.common.StatusCode;
import com.example.readtodomanager.model.Memo;
import com.example.readtodomanager.repository.MemoRepository;
import com.example.readtodomanager.service.dto.CreateMemoServiceInDto;
import com.example.readtodomanager.validator.CommonValidator;

@Service
@Transactional
public class CreateMemoServiceImpl implements CreateMemoService {

    @Autowired
    private MemoRepository memoRepository;

    @Override
    public ResponseEntity<ApiResponse<String>> createMemo(CreateMemoServiceInDto createMemoServiceInDto) {
        // 検証を実行
        Set<ConstraintViolation<CreateMemoServiceInDto>> violations = CommonValidator.validate(createMemoServiceInDto);

        if (!violations.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse<String>(400, "不正なリクエストです"));
        }
        
        // 本がある前提でメモを作成するので特に本の存在チェックはなしとする
        
        // DBに登録するメモの作成処理
        Memo memo = new Memo();
        memo.setBookId(createMemoServiceInDto.getBookId());
        memo.setUserId(createMemoServiceInDto.getUserId());
        memo.setContent(createMemoServiceInDto.getContent());
        memo.setCreatedAt(new Date());

        // DBに登録する処理
        memoRepository.insert(memo);

        return ResponseEntity.ok(new ApiResponse<String>(StatusCode.OK, null));
    }
}
