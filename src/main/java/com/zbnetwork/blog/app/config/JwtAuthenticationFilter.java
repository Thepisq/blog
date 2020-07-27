package com.zbnetwork.blog.app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zbnetwork.blog.app.service.impl.UserUdServiceImpl;
import com.zbnetwork.blog.app.utils.JwtUtil;
import com.zbnetwork.blog.app.utils.role.UserUd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author 13496
 */
@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserUdServiceImpl userUdService;
    private final JwtUtil jwtUtil;

    @Autowired
    public JwtAuthenticationFilter(UserUdServiceImpl userUdService, JwtUtil jwtUtil) {
        this.userUdService = userUdService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        log.info("经过了JwtAuthenticationFilter");
        String headerToken = request.getHeader(jwtUtil.getHeader());
        if (headerToken != null && !headerToken.isEmpty()) {
            String username = Objects.requireNonNull(jwtUtil.getClaims(headerToken).getSubject(), "jwt token 的 subject 不存在");
            log.info("JwtAuthenticationFilter: username = {" + username + "}");
            if (SecurityContextHolder.getContext().getAuthentication() != null) {
                UserUd user = (UserUd) userUdService.loadUserByUsername(username);
                if (!jwtUtil.verify(headerToken, user.getUsername())) {
                    response.setContentType("application/json;charset=UTF-8");
                    //设定返回的Json数据
                    HashMap<String, Object> result = new HashMap<>(8);
                    result.put("status", "fail");
                    result.put("msg", "token不正确");
                    response.getWriter().write(new ObjectMapper().writeValueAsString(result));
                }
            }
        }
        filterChain.doFilter(request, response);

    }
}
