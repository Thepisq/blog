package com.liushaonetwork.blog.app.config.jwtauthentication;

import com.liushaonetwork.blog.app.DO.MyUserDetails;
import com.liushaonetwork.blog.app.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        JwtAuthenticationToken authToken = (JwtAuthenticationToken) authentication;
        String token = authToken.getToken();
        if (isTimeToRefrech(token)) {
            String refreshedtoken = JwtUtil.signToken((MyUserDetails) authToken.getPrincipal());
            response.setHeader(tokenInHeader, tokenPrefix + refreshedtoken);
        }
    }

    private boolean isTimeToRefrech(String token) {
        Claims claims = JwtUtil.getClaims(token);
        LocalDateTime issuedAt = LocalDateTime.ofInstant(claims.getIssuedAt().toInstant(), ZoneId.systemDefault());
        return LocalDateTime.now().minusSeconds(tokenRefreshInterval).isAfter(issuedAt);
    }
}
