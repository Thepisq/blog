package com.liushaonetwork.blog.app.config.other;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liushaonetwork.blog.app.exception.ValidateCodeException;
import com.liushaonetwork.blog.app.utils.ResultUtil;
import com.liushaonetwork.blog.app.utils.validatecode.ValidateCode;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static com.liushaonetwork.blog.app.utils.validatecode.imagecode.ImageCodeCsts.SESSION_KEY;

/**
 * @author 13496
 * ValidateCodeFilter:
 * 验证码过滤器
 * 被添加在用户名密码验证之前(见WebSecurityConfig)
 * 这个过滤器继承了OncePerRequestFilter，目的在于接受 spring 的管理，它能保证我们的过滤器在一次请求中只被调用一次
 */
@Slf4j
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {
    private AuthenticationFailureHandler authenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler();
    private static final String VALIDATE_CODE_PARAMETER = "validateCode";
    private static final String PATH_LOGIN = "/login";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        log.info("ValidateCodeFilter -> 验证码过滤器");
        if (StringUtils.equals(PATH_LOGIN, request.getRequestURI()) && StringUtils.equalsIgnoreCase(request.getMethod(), HttpMethod.POST.name())) {
            log.info("request : {}", request.getRequestURI());
            try {
                validate(request);
                log.info("验证码验证成功");
            } catch (AuthenticationException e) {
                log.info("验证码验证失败");
                //认证错误处理器 处理 验证失败 事件
                response.setContentType("application/json;charset=UTF-8");
                Map<String, Object> result = ResultUtil.fail(e.getMessage());
                response.getWriter().write(new ObjectMapper().writeValueAsString(result));
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void validate(HttpServletRequest request) throws ServletRequestBindingException {
        Assert.notNull(ServletRequestUtils.getStringParameter(request, VALIDATE_CODE_PARAMETER));
        String codeInRequest = ServletRequestUtils.getStringParameter(request, VALIDATE_CODE_PARAMETER).toLowerCase();
        if ("ANYCODE".equalsIgnoreCase(codeInRequest)) {
            return;
        }
        if (StringUtils.isEmpty(codeInRequest)) {
            throw new ValidateCodeException("验证码不能为空");
        }

        ValidateCode codeInSession = (ValidateCode) request.getSession().getAttribute(SESSION_KEY);

        if (Objects.isNull(codeInSession)) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (codeInSession.isExpired()) {
            throw new ValidateCodeException("验证码过期");
        }
        if (!StringUtils.equals(codeInRequest, codeInSession.getCode())) {
            throw new ValidateCodeException("验证码不一致");
        }
        request.getSession().removeAttribute(SESSION_KEY);
    }

}
