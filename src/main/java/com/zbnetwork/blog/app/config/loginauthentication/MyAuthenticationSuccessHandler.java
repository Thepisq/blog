package com.zbnetwork.blog.app.config.loginauthentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zbnetwork.blog.app.utils.JwtUtil;
import com.zbnetwork.blog.app.utils.ResultUtil;
import com.zbnetwork.blog.app.utils.userdetails.UserUd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.zbnetwork.blog.app.utils.Constants.tokenInHeader;

/**
 * @author 13496
 * MyAuthenticationSuccessHandler:
 * 自定义认证成功处理类
 * 在WebSecurityConfig中被指定为登录(.formlogin())的认证成功处理类
 */
@Slf4j
@Component
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;

    @Autowired
    public MyAuthenticationSuccessHandler(ObjectMapper objectMapper, JwtUtil jwtUtil) {
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("经过了MyAuthenticationSuccessHandler -> 登录认证成功");
        String token = jwtUtil.signToken((UserUd) authentication.getPrincipal());
        System.out.println("生成的token: {\n" + token + "\n}");
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader(tokenInHeader, token);
        System.out.println("header里的token: {\n" + response.getHeader(tokenInHeader) + "\n}"
                + "内容: {\n" + jwtUtil.getClaims(token).toString() + "\n}");
        //设定返回的Json数据
        Map<String, Object> result = ResultUtil.success();
        response.getWriter().write(objectMapper.writeValueAsString(result));
        //使用HttpServletResponse.getWriter()方法,容器会在request处理结束后帮你回收相关资源
    }
}
