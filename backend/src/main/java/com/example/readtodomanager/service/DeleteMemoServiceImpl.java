package com.example.readtodomanager.service;

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
import com.example.readtodomanager.service.dto.DeleteMemoServiceInDto;
import com.example.readtodomanager.validator.CommonValidator;

@Service
@Transactional
public class DeleteMemoServiceImpl implements DeleteMemoService {

    @Autowired
    private MemoRepository memoRepository;

    @Override
    public ResponseEntity<ApiResponse<Long>> deleteMemo(DeleteMemoServiceInDto deleteMemoServiceInDto) {
        // 検証を実行
        Set<ConstraintViolation<DeleteMemoServiceInDto>> violations = CommonValidator.validate(deleteMemoServiceInDto);

        if (!violations.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse<Long>(400, "不正なリクエストです"));
        }

        
        // ①メモを取得する
        Memo memo = memoRepository.getMemo(deleteMemoServiceInDto.getId());

        // ②メモが存在しない場合はエラーレスポンスを返す
        if (memo == null) {
            return ResponseEntity.badRequest().body(new ApiResponse<Long>(StatusCode.BAD_REQUEST, "メモが存在しません"));

        }

        // ③メモのuserIdを確認し、deleteMemoServiceInDtoのuserIdと一致しない場合はエラーレスポンスを返す
        if (!memo.getUserId().equals(deleteMemoServiceInDto.getUserId())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(StatusCode.BAD_REQUEST, "メモを削除できません"));
        }

        // ④メモを物理削除する
        memoRepository.delete(memo.getId());

        // ⑤正常レスポンスを返す
        return ResponseEntity.ok(new ApiResponse<Long>(StatusCode.OK, null, memo.getId()));
    }

}
