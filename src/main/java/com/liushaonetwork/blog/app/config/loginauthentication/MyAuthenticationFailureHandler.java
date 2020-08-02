package com.liushaonetwork.blog.app.config.loginauthentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liushaonetwork.blog.app.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author 13496
 * MyAuthenticationFailureHandler:
 * 认证失败处理类
 * 在WebSecurityConfig中被指定为登录(.formlogin())的认证失败处理类
 */
@Slf4j
@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        log.info("MyAuthenticationFailureHandler -> 登录认证失败");
        response.setContentType("application/json;charset=UTF-8");
        //设定返回的Json数据
        Map<String, Object> result = ResultUtil.fail(exception.getMessage());
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
