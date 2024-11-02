package com.example.readtodomanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.readtodomanager.common.ApiResponse;
import com.example.readtodomanager.common.StatusCode;
import com.example.readtodomanager.repository.SessionRepository;
import com.example.readtodomanager.service.dto.LogoutInDto;

@Service
@Transactional
public class LogoutServiceImpl implements LogoutService {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public ResponseEntity<ApiResponse<?>> logout(LogoutInDto logoutIndto) {
        String userId = logoutIndto.getUserId();

        // セッション情報を削除
        sessionRepository.delete(userId);

        return ResponseEntity.ok(new ApiResponse<>(StatusCode.OK, null));
    }

}
