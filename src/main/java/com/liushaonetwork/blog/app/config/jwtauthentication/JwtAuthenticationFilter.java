package com.liushaonetwork.blog.app.config.jwtauthentication;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.liushaonetwork.blog.app.utils.Constants.tokenInHeader;
import static com.liushaonetwork.blog.app.utils.Constants.tokenPrefix;

/**
 * @author 13496
 * JwtAuthenticationFilter:
 * 获取并验证JWT的过滤器类
 */
@Slf4j
@Getter
@Setter
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private AuthenticationManager authenticationManager;
    private AuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
    private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
    private List<RequestMatcher> permissiveRequestMatchers;
    private final RequestMatcher reqMatcher;

    public JwtAuthenticationFilter() {
        this.reqMatcher = new RequestHeaderRequestMatcher(tokenInHeader);
    }

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(authenticationManager, "authenticationManager must be specified");
        Assert.notNull(successHandler, "AuthenticationSuccessHandler must be specified");
        Assert.notNull(failureHandler, "AuthenticationFailureHandler must be specified");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        log.info("JwtAuthenticationFilter -> 获取token");
        if (!reqMatcher.matches(request) || !StringUtils.startsWith(request.getHeader(tokenInHeader), tokenPrefix)) {
            log.info(!reqMatcher.matches(request) ? "token获取不到" : "token格式不对");
            filterChain.doFilter(request, response);
            return;
        }
        log.info("token:{" + tokenInHeader + ": " + request.getHeader(tokenInHeader) + "}");
        String token = StringUtils.removeStart(request.getHeader(tokenInHeader), tokenPrefix);
        Authentication authResult = null;
        AuthenticationException authException = null;
        try {
            if (StringUtils.isNotBlank(token)) {
                JwtAuthenticationToken authToken = new JwtAuthenticationToken(token);
                //交给JwtAuthenticationProvider的authenticate()方法处理
                authResult = getAuthenticationManager().authenticate(authToken);
            } else {
                authException = new InsufficientAuthenticationException("token不能为空");
            }
        } catch (AuthenticationException e) {
            authException = e;
        }
        if (authResult != null) {
            successAuthenticationAction(request, response, filterChain, authResult);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(authResult.getPrincipal(), null, authResult.getAuthorities());
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);
        } else if (!permissiveRequest(request)) {
            unsuccessAuthenticationAction(request, response, authException);
        }

        filterChain.doFilter(request, response);
    }

    protected boolean permissiveRequest(HttpServletRequest request) {
        if (permissiveRequestMatchers == null) {
            return false;
        }
        for (RequestMatcher permissiveMatcher : permissiveRequestMatchers) {
            if (permissiveMatcher.matches(request)) {
                return true;
            }
        }
        return false;
    }

    protected void unsuccessAuthenticationAction(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, authException);
    }

    protected void successAuthenticationAction(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    public void setPermissiveUrl(String... urls) {
        if (permissiveRequestMatchers == null) {
            permissiveRequestMatchers = new ArrayList<>();
        }
        for (String url : urls) {
            permissiveRequestMatchers.add(new AntPathRequestMatcher(url));
        }
    }

}
