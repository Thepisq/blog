package com.liushaonetwork.blog.app.config.jwtauthentication;

import com.liushaonetwork.blog.app.DO.MyUserDetails;
import com.liushaonetwork.blog.app.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.liushaonetwork.blog.app.utils.Constants.*;

/**
 * @author 13496
 * JwtAuthenticationSuccessHandler:
 * token认证成功类
 * 此处用来刷新token
 */
@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtUtil jwtUtil;

    @Autowired
    public JwtAuthenticationSuccessHandler(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        JwtAuthenticationToken authToken = (JwtAuthenticationToken) authentication;
        String token = authToken.getToken();
        if (isTimeToRefrech(token)) {
            String refreshedtoken = jwtUtil.signToken((MyUserDetails) authToken.getPrincipal());
            response.setHeader(tokenInHeader, TOKEN_PREFIX + refreshedtoken);
        }
    }

    private boolean isTimeToRefrech(String token) {
        Claims claims = jwtUtil.getClaims(token);
        LocalDateTime issuedAt = LocalDateTime.ofInstant(claims.getIssuedAt().toInstant(), ZoneId.systemDefault());
        return LocalDateTime.now().minusSeconds(TOKEN_REFRESH_INTERVAL).isAfter(issuedAt);
    }
}
