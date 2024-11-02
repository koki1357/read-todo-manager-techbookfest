package com.example.readtodomanager.common.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.readtodomanager.common.AuthConst;
import com.example.readtodomanager.model.Session;
import com.example.readtodomanager.repository.SessionRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Authorizationヘッダからトークンを取得
        String token = request.getHeader(AuthConst.HEADER_TOKEN);
        String userId = request.getHeader(AuthConst.HEADER_KEY);

        Session session = sessionRepository.getSession(userId);

        if (session == null) {
            // セッションが存在しない場合、エラーを返す
            return false;
        }

        if (!session.getToken().equals(token)) {
            // トークンが一致しない場合、エラーを返す
            return false;
        }

        // TODO: currentTimeMillis使わない方が良いかも
        if (session.getExpiredAt().getTime() < System.currentTimeMillis()) {
            // トークンの有効期限が切れている場合、エラーを返す
            return false;
        }

        // トークンが有効な場合、リクエストを続行
        return true;
    }

    
}
