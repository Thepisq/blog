package com.zbnetwork.blog.app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author 13496
 * 自定义登录成功
 */
@Slf4j
@Component
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("MyAuthenticationSuccessHandler -> 登录成功");
        response.setContentType("application/json;charset=UTF-8");
        //设定返回的Json数据
        HashMap<String, Object> result = new HashMap<>(8);
        result.put("status", "success");
        response.getWriter().write(objectMapper.writeValueAsString(result));
        //使用HttpServletResponse.getWriter()方法
        //容器会在request处理结束后帮你回收相关资源
    }
}
