package com.example.readtodomanager.service;

import java.util.Date;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.readtodomanager.common.ApiResponse;
import com.example.readtodomanager.common.StatusCode;
import com.example.readtodomanager.model.LoginResponse;
import com.example.readtodomanager.model.Session;
import com.example.readtodomanager.model.User;
import com.example.readtodomanager.repository.SessionRepository;
import com.example.readtodomanager.repository.UserRepository;
import com.example.readtodomanager.service.dto.LoginServiceInDto;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public ResponseEntity<ApiResponse<LoginResponse>> login(LoginServiceInDto loginServiceInDto) {
        
        // userテーブルにアクセスしuser情報取得
        User user = userRepository.getUser(loginServiceInDto.getUserId());

        // ユーザーが存在しなかったらエラーを返す
        if (user == null) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(StatusCode.BAD_REQUEST, "ユーザIDもしくはパスワードが間違っています"));
        }

        // パスワードを比較し、一致していなかったらエラーを返す
        if (!BCrypt.checkpw(loginServiceInDto.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(StatusCode.BAD_REQUEST, "ユーザIDもしくはパスワードが間違っています"));
        }

        // ログイン済みの場合は、トークンの作成はしない
        Session session = sessionRepository.getSession(user.getUserId());

        if (session != null) {
            LoginResponse response = new LoginResponse();
            response.setUserId(session.getUserId());
            response.setToken(session.getToken());

            return ResponseEntity.ok(new ApiResponse<LoginResponse>(StatusCode.OK, null, response));
        }

        // トークンを生成する
        String token = UUID.randomUUID().toString();

        // セッションテーブルにuserId, token, expirationを登録
        Session newSession = new Session();
        newSession.setUserId(user.getUserId());
        newSession.setToken(token);
        // 有効期限は12時間
        Date expiredAt = new Date(System.currentTimeMillis() + 3600000*12);
        newSession.setExpiredAt(expiredAt);
        sessionRepository.insert(newSession);

        LoginResponse response = new LoginResponse();
        response.setUserId(user.getUserId());
        response.setToken(token);

        return ResponseEntity.ok(new ApiResponse<LoginResponse>(StatusCode.OK, null, response));

    }

}
