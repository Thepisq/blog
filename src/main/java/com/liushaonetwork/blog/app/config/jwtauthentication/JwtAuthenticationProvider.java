package com.liushaonetwork.blog.app.config.jwtauthentication;

import com.liushaonetwork.blog.app.DO.MyUserDetails;
import com.liushaonetwork.blog.app.service.impl.MyUserDetailsService;
import com.liushaonetwork.blog.app.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.NonceExpiredException;

import java.util.Calendar;

import static com.liushaonetwork.blog.app.utils.Constants.tokenPayloadUsername;

/**
 * JwtAuthenticationProvider:
 * 根据JWT验证用户，作用如同DaoAuthenticationProvider之于UsernamepasswordAuthentication
 */
@Slf4j
public class JwtAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private MyUserDetailsService userUdService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("JwtAuthenticationProvider -> 认证token");
        String token = ((JwtAuthenticationToken) authentication).getToken();
        Claims claims = JwtUtil.getClaims(token);
        if (claims.getExpiration().before(Calendar.getInstance().getTime())) {
            throw new NonceExpiredException("Token 过期");
        }
        String username = (String) claims.get(tokenPayloadUsername);
        MyUserDetails user = (MyUserDetails) userUdService.loadUserByUsername(username);
        if (user == null) {
            throw new NonceExpiredException("Token 信息错误");
        }
        return new JwtAuthenticationToken(user, token, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(JwtAuthenticationToken.class);
    }
}
