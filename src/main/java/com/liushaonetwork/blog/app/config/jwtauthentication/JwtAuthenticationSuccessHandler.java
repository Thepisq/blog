package com.liushaonetwork.blog.app.config.jwtauthentication;

import com.liushaonetwork.blog.app.DO.MyUserDetails;
import com.liushaonetwork.blog.app.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        log.info("JwtAuthenticationSuccessHandler -> jwt认证成功");
        JwtAuthenticationToken authToken = (JwtAuthenticationToken) authentication;
        String token = authToken.getToken();
        if (isTimeToRefresh(token)) {
            String refresher = JwtUtil.signToken((MyUserDetails) authToken.getPrincipal());
            response.setHeader(tokenInHeader, tokenPrefix + refresher);
        }
    }

    private boolean isTimeToRefresh(String token) {
        try {
            Claims claims = JwtUtil.getClaims(token);
            log.info("Claims: {\n" + claims.toString() + "}");
            LocalDateTime issuedAt = LocalDateTime.ofInstant(claims.getIssuedAt().toInstant(), ZoneId.systemDefault());
            return LocalDateTime.now().minusSeconds(tokenRefreshInterval).isAfter(issuedAt);
        } catch (Exception e) {
            return false;
        }
    }
}
