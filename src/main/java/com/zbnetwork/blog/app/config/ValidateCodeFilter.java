package com.zbnetwork.blog.app.config;

import com.zbnetwork.blog.app.exception.ValidateCodeException;
import com.zbnetwork.blog.app.utils.validatecode.ValidateCode;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.zbnetwork.blog.app.utils.validatecode.ValidateCodeCsts.SESSION_KEY;

/**
 * @author 13496
 * 这个过滤器继承了OncePerRequestFilter，目的在于接受 spring 的管理，它能保证我们的过滤器在一次请求中只被调用一次
 */
@Slf4j
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements Filter {
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final SessionStrategy sessionStrategy;
    private static final String VALIDATE_CODE_PARAMETER = "validateCode";
    private static final String PATH_LOGIN = "/login";

    @Autowired
    public ValidateCodeFilter(AuthenticationFailureHandler authenticationFailureHandler, SessionStrategy sessionStrategy) {
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.sessionStrategy = sessionStrategy;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (StringUtils.equals(PATH_LOGIN, request.getRequestURI()) && StringUtils.equalsIgnoreCase(request.getMethod(), HttpMethod.POST.name())) {
            log.info("request : {}", request.getRequestURI());
            try {
                validate(new ServletWebRequest(request));
            } catch (AuthenticationException e) {
                log.info("验证码验证失败");
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        log.info("验证码验证成功");
        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        Assert.notNull(ServletRequestUtils.getStringParameter(request.getRequest(), VALIDATE_CODE_PARAMETER));
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), VALIDATE_CODE_PARAMETER).toLowerCase();
        if (StringUtils.isEmpty(codeInRequest)) {
            throw new ValidateCodeException("验证码不能为空");
        }
        ValidateCode codeInSession = (ValidateCode) sessionStrategy.getAttribute(request, SESSION_KEY);
        if (Objects.isNull(codeInSession)) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (codeInSession.isExpired()) {
            throw new ValidateCodeException("验证码过期");
        }
        if (!StringUtils.equals(codeInRequest, codeInSession.getCode())) {
            throw new ValidateCodeException("验证码不一致");
        }
        sessionStrategy.removeAttribute(request, SESSION_KEY);
    }
}
