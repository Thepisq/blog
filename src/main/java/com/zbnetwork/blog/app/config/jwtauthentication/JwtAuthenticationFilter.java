package com.zbnetwork.blog.app.config.jwtauthentication;

import com.zbnetwork.blog.app.service.impl.UserUdServiceImpl;
import com.zbnetwork.blog.app.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static com.zbnetwork.blog.app.utils.Constants.tokenInHeader;

/**
 * @author 13496
 * JwtAuthenticationFilter:
 * 验证JWT的过滤器类
 */
@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final RequestMatcher reqMatcher;

    @Autowired
    public JwtAuthenticationFilter(UserUdServiceImpl userUdService, JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        this.reqMatcher = new RequestHeaderRequestMatcher("Authorization");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        log.info("经过了JwtAuthenticationFilter");
        log.info("检查jwt: " + request.getHeader(tokenInHeader));
        if (!reqMatcher.matches(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        log.info("JwtFilter> \n  header: \n    " + request.getHeader(tokenInHeader));
        String token = request.getHeader(tokenInHeader);
        Claims claims;
        try {
            claims = jwtUtil.getClaims(token);
        } catch (Exception e) {
            log.info("token解析过程中发生错误: {\n" + e.getMessage() + "}\n");
            filterChain.doFilter(request, response);
            return;
        }
        String username = (String) claims.get("username");
        Collection<? extends GrantedAuthority> roles = (Collection<? extends GrantedAuthority>) claims.get("roles");
        JwtAuthenticationToken authentication = new JwtAuthenticationToken(username, roles);
        authentication.setDetails(new WebAuthenticationDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("header中获取的token: " + jwtUtil.getClaims(token).toString());
        filterChain.doFilter(request, response);

    }


}
