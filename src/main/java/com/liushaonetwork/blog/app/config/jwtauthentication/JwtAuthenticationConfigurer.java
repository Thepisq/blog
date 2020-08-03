package com.liushaonetwork.blog.app.config.jwtauthentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;

/**
 * @author 13496
 * JwtAuthenticationConfigurer:
 * 整合JwtAuthentication
 */
public class JwtAuthenticationConfigurer<T extends JwtAuthenticationConfigurer<T, B>, B extends HttpSecurityBuilder<B>> extends AbstractHttpConfigurer<T, B> {
    private JwtAuthenticationFilter authFilter;

    public JwtAuthenticationConfigurer() {
        this.authFilter = new JwtAuthenticationFilter();
    }

    @Override
    public void configure(B http) throws Exception {
        //在WebSecurityConfig.java用@Bean注入，下一行设置的Manager不会为null
        authFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        JwtAuthenticationFilter filter = postProcess(authFilter);
        http.addFilterBefore(authFilter, LogoutFilter.class);
    }

    public JwtAuthenticationConfigurer<T, B> permissiveRequestUrls(String... urls) {
        authFilter.setPermissiveUrl(urls);
        return this;
    }

    public JwtAuthenticationConfigurer<T, B> successHandler(AuthenticationSuccessHandler successHandler) {
        authFilter.setSuccessHandler(successHandler);
        return this;
    }

    public JwtAuthenticationConfigurer<T, B> failureHandler(AuthenticationFailureHandler failureHandler) {
        authFilter.setFailureHandler(failureHandler);
        return this;
    }
}
