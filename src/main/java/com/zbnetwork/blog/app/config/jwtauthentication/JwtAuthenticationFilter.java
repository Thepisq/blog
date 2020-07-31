package com.zbnetwork.blog.app.config.jwtauthentication;

import com.zbnetwork.blog.app.service.impl.UserUdServiceImpl;
import com.zbnetwork.blog.app.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

/**
 * @author 13496
 * JwtAuthenticationFilter:
 * 验证JWT的过滤器类
 */
@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserUdServiceImpl userUdService;
    private final JwtUtil jwtUtil;
    private final RequestMatcher reqMatcher;

    @Autowired
    public JwtAuthenticationFilter(UserUdServiceImpl userUdService, JwtUtil jwtUtil) {
        this.userUdService = userUdService;
        this.jwtUtil = jwtUtil;
        this.reqMatcher = new RequestHeaderRequestMatcher("Authorization");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        log.info("经过了JwtAuthenticationFilter");

        if (!reqMatcher.matches(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String tokenInHeader = StringUtils.removeStart(request.getHeader("Authorization"), "Bearer ");
        Claims claims;
        try {
            claims = jwtUtil.getClaims(tokenInHeader);
        } catch (Exception e) {
            System.out.println("token解析过程中发生错误: {\n" + e.getMessage() + "}\n");
            filterChain.doFilter(request, response);
            return;
        }
        String username = (String) claims.get("username");
        Collection<? extends GrantedAuthority> roles = (Collection<? extends GrantedAuthority>) claims.get("roles");
        JwtAuthenticationToken authentication = new JwtAuthenticationToken(username, roles);
        authentication.setDetails(new WebAuthenticationDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);

    }


}
