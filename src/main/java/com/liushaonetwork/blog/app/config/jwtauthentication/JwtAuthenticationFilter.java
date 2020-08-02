package com.liushaonetwork.blog.app.config.jwtauthentication;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.liushaonetwork.blog.app.utils.Constants.TOKEN_PREFIX;
import static com.liushaonetwork.blog.app.utils.Constants.tokenInHeader;

/**
 * @author 13496
 * JwtAuthenticationFilter:
 * 获取并验证JWT的过滤器类
 */
@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private AuthenticationManager authenticationManager;
    private AuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
    private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
    private List<RequestMatcher> permissiveRequestMatchers;
    private final RequestMatcher reqMatcher;

    @Autowired
    public JwtAuthenticationFilter() {
        this.reqMatcher = new RequestHeaderRequestMatcher("Authorization");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        log.info("经过了JwtAuthenticationFilter");
        log.info("检查jwt: " + request.getHeader(tokenInHeader));
        if (!reqMatcher.matches(request) || !request.getHeader(tokenInHeader).startsWith(TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        log.info("JwtFilter> \n  header: \n    " + request.getHeader(tokenInHeader));
        String token = StringUtils.removeStart(request.getHeader(tokenInHeader), TOKEN_PREFIX);

        try {
            JwtAuthenticationToken auth = new JwtAuthenticationToken(token);
        } catch (Exception e) {
            log.info("token解析过程中发生错误: {\n" + e.getMessage() + "}\n");
            filterChain.doFilter(request, response);
            return;
        }

        filterChain.doFilter(request, response);

    }


    public void setPermissiveUrl(String... urls) {
        if (permissiveRequestMatchers == null) {
            permissiveRequestMatchers = new ArrayList<>();
        }
        for (String url : urls) {
            permissiveRequestMatchers.add(new AntPathRequestMatcher(url));
        }
    }

    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    protected AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    @Override
    public void afterPropertiesSet() throws ServletException {
        //Assert.notNull(authenticationManager, "authenticationManager不能为空");
        Assert.notNull(successHandler, "successHandler不能为空");
        Assert.notNull(failureHandler, "failureHandler不能为空");
    }
}
