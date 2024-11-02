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
import com.example.readtodomanager.service.dto.UpdateMemoServiceInDto;
import com.example.readtodomanager.validator.CommonValidator;


@Service
@Transactional
public class UpdateMemoServiceImpl implements UpdateMemoService {

    @Autowired
    private MemoRepository memoRepository;

    /**
     * ブックメモを更新します。
     *
     * @param updateBookMemoServiceInDto 更新するブックメモの情報を含むDTOオブジェクト
     * @return レスポンスエンティティ。更新が成功した場合は成功ステータスコードと更新されたメモのIDを含むApiResponseを返します。
     *         更新が失敗した場合は失敗ステータスコードとエラーメッセージを含むApiResponseを返します。
     */
    @Override
    public ResponseEntity<ApiResponse<Long>> updateBookMemo(UpdateMemoServiceInDto updateBookMemoServiceInDto) {
        // 検証を実行
        Set<ConstraintViolation<UpdateMemoServiceInDto>> violations = CommonValidator.validate(updateBookMemoServiceInDto);

        if (!violations.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse<Long>(StatusCode.BAD_REQUEST, "不正なリクエストです"));
        }
        
        Memo memo = memoRepository.getMemo(updateBookMemoServiceInDto.getId());

        // めもが存在しない場合はエラーを返す
        if (memo == null) {
            return ResponseEntity.badRequest().body(new ApiResponse<Long>(StatusCode.BAD_REQUEST, "本が存在しません"));
        }


        // リクエストユーザが登録したメモ以外の更新は不可
        if (!memo.getUserId().equals(updateBookMemoServiceInDto.getUserId())) {
            return ResponseEntity.badRequest().body(new ApiResponse<Long>(StatusCode.BAD_REQUEST, "削除できません"));
        }

        // メモの内容を更新
        memo.setContent(updateBookMemoServiceInDto.getContent());
        memo.setUpdatedAt(new Date());

        // 更新処理
        memoRepository.update(memo);

        return ResponseEntity.ok(new ApiResponse<>(StatusCode.OK, null, memo.getId()));
    }

}
