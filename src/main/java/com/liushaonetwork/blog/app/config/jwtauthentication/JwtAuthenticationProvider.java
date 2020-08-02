package com.liushaonetwork.blog.app.config.jwtauthentication;

import com.liushaonetwork.blog.app.DO.MyUserDetails;
import com.liushaonetwork.blog.app.service.impl.MyUserDetailsService;
import com.liushaonetwork.blog.app.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.NonceExpiredException;

import java.util.Calendar;

import static com.liushaonetwork.blog.app.utils.Constants.TOKEN_PAYLOAD_USERNAME;

/**
 * JwtAuthenticationProvider:
 * 根据JWT验证用户，作用如同DaoAuthenticationProvider之于UsernamepasswordAuthentication
 */
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final MyUserDetailsService userUdService;
    private final JwtUtil jwtUtil;

    @Autowired
    public JwtAuthenticationProvider(MyUserDetailsService userUdService, JwtUtil jwtUtil) {
        this.userUdService = userUdService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = ((JwtAuthenticationToken) authentication).getToken();
        Claims claims = jwtUtil.getClaims(token);
        if (claims.getExpiration().before(Calendar.getInstance().getTime())) {
            throw new NonceExpiredException("Token 过期");
        }
        String username = (String) claims.get(TOKEN_PAYLOAD_USERNAME);
        MyUserDetails user = (MyUserDetails) userUdService.loadUserByUsername(username);
        if (user == null) {
            throw new NonceExpiredException("Token 过期");
        }
        JwtAuthenticationToken authToken = new JwtAuthenticationToken(user, token, user.getAuthorities());
        return authToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(JwtAuthenticationToken.class);
    }
}
