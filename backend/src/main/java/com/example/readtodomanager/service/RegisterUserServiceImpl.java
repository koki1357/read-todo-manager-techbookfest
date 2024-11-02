package com.example.readtodomanager.service;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.readtodomanager.common.ApiResponse;
import com.example.readtodomanager.common.StatusCode;
import com.example.readtodomanager.model.User;
import com.example.readtodomanager.repository.UserRepository;
import com.example.readtodomanager.service.dto.RegisterUserServiceInDto;
import com.example.readtodomanager.validator.CommonValidator;

@Service
@Transactional
public class RegisterUserServiceImpl implements RegisterUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<ApiResponse<?>> registerUser(RegisterUserServiceInDto inDto) {
        // 型チェック
        Set<ConstraintViolation<RegisterUserServiceInDto>> violations = CommonValidator.validate(inDto);

        if (!violations.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(StatusCode.BAD_REQUEST, "不正なリクエストです"));
        }

        // リクエストのユーザがすでに登録されているかチェック
        User target = userRepository.getUser(inDto.getUserId());

        // すでに登録されている場合はエラーレスポンスを返す
        if (target != null) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(StatusCode.BAD_REQUEST, "すでに登録されているユーザです"));
        }

        String encodedPassword = BCrypt.hashpw(inDto.getPassword(), BCrypt.gensalt());

        // ユーザー情報を登録
        User user = new User();
        user.setUserId(inDto.getUserId());
        user.setPassword(encodedPassword);
        userRepository.insert(user);

        return ResponseEntity.ok(new ApiResponse<>(StatusCode.OK, null));
    }

}
