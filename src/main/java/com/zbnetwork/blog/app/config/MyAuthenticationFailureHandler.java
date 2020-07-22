package com.zbnetwork.blog.app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author 13496
 */
@Slf4j
@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        log.info("MyAuthenticationFailureHandler -> 登录失败");
        response.setContentType("application/json;charset=UTF-8");
        //设定返回的Json数据
        HashMap<String, Object> result = new HashMap(8);
        result.put("status", "fail");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
